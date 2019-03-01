package com.hualala.domain.model;

import android.support.annotation.IntDef;
import com.hualala.data.entity.data.VideoEntity;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MVideo extends VideoEntity {

    @MVideoType
    @Setter
    @Getter
    public int type;// 1-video  2-audio  3-jpg

    public static final int MVideoTypeVideo = 1;
    public static final int MVideoTypeAudio = 2;
    public static final int MVideoTypePicture = 3;
    public static final int MVideoTypeApk = 4;

    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.PARAMETER, ElementType.FIELD})
    @IntDef({MVideoTypeVideo, MVideoTypeAudio, MVideoTypePicture, MVideoTypeApk})
    public @interface MVideoType {

    }

}
