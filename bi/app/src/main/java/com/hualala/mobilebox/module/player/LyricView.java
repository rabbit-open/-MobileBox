package com.hualala.mobilebox.module.player;

import android.text.Layout;
import android.text.StaticLayout;
import android.util.TypedValue;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LyricView {

    public LyricInfo mLyricInfo;
    public int mLineCount;
    public String mCurrentLyricFilePath;


    private void setupLyricResource(InputStream inputStream, String charsetName) {
        if (inputStream != null) {
            try {
                LyricInfo lyricInfo = new LyricInfo();
                lyricInfo.songLines = new ArrayList<>();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charsetName);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    analyzeLyric(lyricInfo, line);
                }
                reader.close();
                inputStream.close();
                inputStreamReader.close();

                mLyricInfo = lyricInfo;
                mLineCount = mLyricInfo.songLines.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resetLyricInfo();
        }

    }

    /**
     * 逐行解析歌词内容
     */
    private void analyzeLyric(LyricInfo lyricInfo, String line) {
        int index = line.lastIndexOf("]");
        if (line.startsWith("[offset:")) {
            // time offset
            lyricInfo.songOffset = Long.parseLong(line.substring(8, index).trim());
            return;
        }
        if (line.startsWith("[ti:")) {
            // title
            lyricInfo.songTitle = line.substring(4, index).trim();
            return;
        }
        if (line.startsWith("[ar:")) {
            // artist
            lyricInfo.songArtist = line.substring(4, index).trim();
            return;
        }
        if (line.startsWith("[al:")) {
            // album
            lyricInfo.songAlbum = line.substring(4, index).trim();
            return;
        }
        if (line.startsWith("[by:")) {
            return;
        }
        if (index >= 9 && line.trim().length() > index + 1) {
            // lyrics
            LineInfo lineInfo = new LineInfo();
            lineInfo.content = line.substring(10, line.length());
            lineInfo.start = measureStartTimeMillis(line.substring(0, index));
            lyricInfo.songLines.add(lineInfo);
        }
        if (index >= 9 && line.trim().length() == index + 1) {
            // lyrics
            LineInfo lineInfo = new LineInfo();
            lineInfo.content = " music";
            lineInfo.start = measureStartTimeMillis(line.substring(0, index));
            lyricInfo.songLines.add(lineInfo);
        }
    }

    /**
     * 从字符串中获得时间值
     */
    private long measureStartTimeMillis(String str) {
        long minute = Long.parseLong(str.substring(1, 3));
        long second = Long.parseLong(str.substring(4, 6));
        long millisecond = Long.parseLong(str.substring(7, 9));
        return millisecond + second * 1000 + minute * 60 * 1000;
    }

    private void resetLyricInfo() {
        if (mLyricInfo != null) {
            if (mLyricInfo.songLines != null) {
                mLyricInfo.songLines.clear();
                mLyricInfo.songLines = null;
            }
            mLyricInfo = null;
        }
    }


    public class LyricInfo {
        List<LineInfo> songLines;
        String songArtist;
        String songTitle;
        String songAlbum;
        long songOffset;

    }

    public class LineInfo {
        String content;
        long start;
    }


    public void setLyricFile(File file) {

        if (file == null || !file.exists()) {
            mCurrentLyricFilePath = "";
            return;
        } else if (file.getPath().equals(mCurrentLyricFilePath)) {
            return;
        } else {
            mCurrentLyricFilePath = file.getPath();
        }
        try {

            FileInputStream fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }

            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            if (encoding != null) {
                setLyricFile(file, encoding);
            } else {
                setLyricFile(file, "UTF-8");
            }
            detector.reset();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLyricFile(File file, String charsetName) {
        if (file != null && file.exists()) {
            try {
                setupLyricResource(new FileInputStream(file), charsetName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
           resetLyricInfo();
        }
    }

}
