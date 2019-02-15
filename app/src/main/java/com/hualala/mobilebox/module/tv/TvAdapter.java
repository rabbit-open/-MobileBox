package com.hualala.mobilebox.module.tv;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hualala.mobilebox.R;
import com.hualala.mobilebox.module.UINavgation;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerAdapter;
import com.hualala.mobilebox.widget.recyclelib.SupetRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TvAdapter extends SupetRecyclerAdapter<TvModel> {

    private Fragment fragment;

    private List<TvModel> data = new ArrayList<>();

    public TvAdapter(Context mContext, Fragment fragment) {
        super(mContext);
        this.fragment = fragment;
        data.add(new TvModel("CCTV-1综合", "http://223.110.242.130:6610/gitv/live1/G_CCTV-1-HQ/1.m3u8"));
        data.add(new TvModel("CCTV-2财经", "http://112.50.243.10/PLTV/88888888/224/3221225800/1.m3u8"));
        data.add(new TvModel("CCTV-3综艺", "http://183.207.249.14/PLTV/3/224/3221225588/index.m3u8"));
        data.add(new TvModel("CCTV-4中文国际", "http://223.110.242.130:6610/gitv/live1/G_CCTV-4-HQ/1.m3u8"));
        data.add(new TvModel("CCTV-5体育", "http://223.110.243.172/PLTV/3/224/3221227166/index.m3u8"));
        data.add(new TvModel("CCTV-5+体育赛事", "http://223.110.243.162/PLTV/3/224/3221225604/index.m3u8"));
        data.add(new TvModel("CCTV-6电影", "http://223.110.243.139/PLTV/3/224/3221225548/index.m3u8"));
        data.add(new TvModel("CCTV-7军事农业", "http://112.50.243.8/PLTV/88888888/224/3221225805/1.m3u8"));
        data.add(new TvModel("CCTV-8电视剧", "http://223.110.243.171/PLTV/3/224/3221227204/index.m3u8"));
        data.add(new TvModel("CCTV-9记录", "http://112.50.243.8/PLTV/88888888/224/3221225820/1.m3u8"));
        data.add(new TvModel("CCTV-10科教", "http://183.207.249.14/PLTV/3/224/3221225550/index.m3u8"));
        data.add(new TvModel("CCTV-11戏曲", "http://112.50.243.8/PLTV/88888888/224/3221225815/1.m3u8"));
        data.add(new TvModel("CCTV-12社会与法", "http://223.110.245.172/PLTV/3/224/3221225556/index.m3u8"));
        data.add(new TvModel("CCTV-13新闻", "http://112.50.243.8/PLTV/88888888/224/3221225817/1.m3u8"));
        data.add(new TvModel("CCTV-14少儿", "http://223.110.245.169/PLTV/3/224/3221227201/index.m3u8"));
        data.add(new TvModel("CCTV-15音乐", "http://112.50.243.8/PLTV/88888888/224/3221225818/1.m3u8"));
        data.add(new TvModel("湖南卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225827/1.m3u8"));
        data.add(new TvModel("江苏卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225847/1.m3u8"));
        data.add(new TvModel("浙江卫视高清", "http://223.110.243.153/PLTV/3/224/3221227215/index.m3u8"));
        data.add(new TvModel("东方卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225828/1.m3u8"));
        data.add(new TvModel("北京卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225826/1.m3u8"));
        data.add(new TvModel("广东卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225824/1.m3u8"));
        data.add(new TvModel("深圳卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225848/1.m3u8"));
        data.add(new TvModel("天津卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225830/1.m3u8"));
        data.add(new TvModel("安徽卫视高清", "http://223.110.243.140/PLTV/3/224/3221225634/index.m3u8"));
        data.add(new TvModel("山东卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225843/1.m3u8"));
        data.add(new TvModel("湖北卫视高清", "http://223.110。243.140/PLTV/3/224/3221227211/index.m3u8"));
        data.add(new TvModel("辽宁卫视高清", "http://223.110.243.157/PLTV/3/224/3221225566/index.m3u8"));
        data.add(new TvModel("重庆卫视高清", "http://223.110.243.138/PLTV/3/224/3221227421/index.m3u8"));
        data.add(new TvModel("江西卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225868/1.m3u8"));
        data.add(new TvModel("河北卫视高清", "http://weblive.hebtv.com/live/hbws_bq/index.m3u8"));
        data.add(new TvModel("黑龙江卫视高清", "http://112.50.243.8/PLTV/88888888/224/3221225862/1.m3u8"));
        data.add(new TvModel("四川卫视", "http://ott.js.chinamobile.com/PLTV/3/224/3221225814/index.m3u8"));
        data.add(new TvModel("广西卫视", "http://223.110.243.162/PLTV/3/224/3221225554/index.m3u8"));
        data.add(new TvModel("河南卫视", "http://112.50.243.8/PLTV/88888888/224/3221225842/1.m3u8"));
        data.add(new TvModel("山西卫视", "http://112.50.243.8/PLTV/88888888/224/3221225839/1.m3u8"));
        data.add(new TvModel("东南卫视", "http://223.110.243.158/PLTV/3/224/3221225598/index.m3u8"));
        data.add(new TvModel("厦门卫视", "http://ott.js.chinamobile.com/PLTV/3/224/3221226093/index.m3u8"));
        data.add(new TvModel("云南卫视", "http://112.50.243.8/PLTV/88888888/224/3221225853/1.m3u8"));
        data.add(new TvModel("宁夏卫视", "http://223.110.243.166/PLTV/3/224/3221225628/1.m3u8"));
        data.add(new TvModel("新疆卫视", "http://223.110.243.140/PLTV/3/224/3221225523/index.m3u8"));
        data.add(new TvModel("旅游卫视", "http://112.50.243.8/PLTV/88888888/224/3221225855/1.m3u8"));
        data.add(new TvModel("内蒙古卫视", "http://223.110.243.142/PLTV/3/224/3221225624/index.m3u8"));
        data.add(new TvModel("凤凰中文台超清", "http://223.110.243.146/PLTV/3/224/3221226922/index.m3u8"));
        data.add(new TvModel("凤凰资讯台超清", "http://223.110.243.146/PLTV/3/224/3221226923/index.m3u8"));
        data.add(new TvModel("凤凰香港台超清", "http://223.110.243.146/PLTV/3/224/3221226975/index.m3u8"));
        data.add(new TvModel("Newtv动作电影", "http://183.207.249.15/PLTV/3/224/3221225529/index.m3u8"));
        data.add(new TvModel("Newtv惊悚悬疑", "http://183.207.249.7/PLTV/3/224/3221225561/index.m3u8"));
        data.add(new TvModel("Newtv精品电影", "http://183.207.249.14/PLTV/3/224/3221225567/index.m3u8"));
        data.add(new TvModel("Newtv明星大片", "http://183.207.249.14/PLTV/3/224/3221225535/index.m3u8"));
        data.add(new TvModel("Newtv家庭剧场", "http://183.207.249.14/PLTV/3/224/3221225549/index.m3u8"));
        data.add(new TvModel("Newtv精品大剧", "http://183.207.249.14/PLTV/3/224/3221225569/index.m3u8"));
        data.add(new TvModel("Newtv金牌综艺", "http://183.207.249.16/PLTV/3/224/3221225559/index.m3u8"));
        data.add(new TvModel("Newtv精品记录", "http://183.207.249.14/PLTV/3/224/3221225557/index.m3u8"));
        data.add(new TvModel("Newtv精品体育", "http://183.207.249.16/PLTV/3/224/3221225543/index.m3u8"));
        data.add(new TvModel("Newtv北京纪实", "http://117.169.79.101:8080/PLTV/88888888/224/3221225764/index.m3u8"));
        data.add(new TvModel("Newtv上海纪实", "http://huaweicdn.hb.chinamobile.com/PLTV/88888888/224/3221225769/3.m3u8"));
        data.add(new TvModel("Newtv纪实天下(电)", "rtsp://120.205.96.36:554/live/ch16022917175374294745.sdp?playtype=1&boid=001&backupagent=120.205.32.36:554&clienttype=1&time=20161205230814+08&life=172800&ifpricereqsnd=1&vcdnid=001&userid=&mediaid=ch16022917175374294745&ctype=2&TSTVTimeLife=60&contname=&authid=0&terminalflag=1&UserLiveType=0&stbid=&nodelevel=3"));
        data.add(new TvModel("Newtv全纪实", "http://huaweicdn.hb.chinamobile.com/PLTV/88888888/224/3221225791/3.m3u8"));
        data.add(new TvModel("Newtv欢笑剧场", "http://huaweicdn.hb.chinamobile.com/PLTV/88888888/224/3221225776/3.m3u8"));
        data.add(new TvModel("Newtv都市剧场", "http://huaweicdn.hb.chinamobile.com/PLTV/88888888/224/3221225754/3.m3u8"));
        data.add(new TvModel("科幻电影", "http://aldirect.hls.huya.com/huyalive/28466698-2689656864-11551988268341919744-2847699194-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("速度与激情", "http://aldirect.hls.huya.com/huyalive/29169025-2686219962-11537226886652362752-2710080226-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("赌博电影", "http://js.hls.huya.com/huyalive/29106097-2689446042-11551082794746642432-2789253870-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("赌神全集新木乃伊", "http://aldirect.hls.huya.com/huyalive/29169025-2686220018-11537227127170531328-2847699120-10057-A-1524041208-1_1200.m3u8"));
        data.add(new TvModel("王晶导演", "https://aldirect.hls.huya.com/huyalive/94525224-2579683592-11079656661667807232-2847687574-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("徐克导演", "http://tx.hls.huya.com/huyalive/29106097-2689447148-11551087544980471808-2789253872-10057-A-1525420294-1.m3u8"));
        data.add(new TvModel("黄渤", "https://aldirect.hls.huya.com/huyalive/30765679-2554414680-10971127511022305280-3048991634-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("徐峥", "https://aldirect.hls.huya.com/huyalive/29106097-2689278568-11550363499393712128-2710090468-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("黄百鸣", "http://ws4.streamhls.huya.com/huyalive/29169025-2686220062-11537227316149092352-2777037828-10057-A-1524041857-1_1200/playlist.m3u8"));
        data.add(new TvModel("斯坦森", "http://tx.hls.huya.com/huyalive/30765679-2554414705-10971127618396487680-3048991636-10057-A-0-1.m3u8"));
        data.add(new TvModel("陈小春", "http://ws4.streamhls.huya.com/huyalive/30765679-2523417522-10837995731143360512-2777068634-10057-A-0-1_1200/playlist.m3u8"));
        data.add(new TvModel("黄日华", "http://ws4.streamhls.huya.com/huyalive/30765679-2523417506-10837995662423883776-2777071322-10057-A-0-1_1200/playlist.m3u8"));
        data.add(new TvModel("甄子丹", "http://ws4.streamhls.huya.com/huyalive/29169025-2686219938-11537226783573147648-2847699096-10057-A-1524024759-1_1200/playlist.m3u8"));
        data.add(new TvModel("李连杰", "http://js.hls.huya.com/huyalive/94525224-2460686093-10568566295157014528-2789253848-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("刘德华", "http://aldirect.hls.huya.com/huyalive/94525224-2467341872-10597152648291418112-2789274550-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("成龙", "http://aldirect.hls.huya.com/huyalive/94525224-2460685722-10568564701724147712-2789253838-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("周星驰", "http://aldirect.hls.huya.com/huyalive/94525224-2460685313-10568562945082523648-2789274524-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("林正英", "http://js.hls.huya.com/huyalive/94525224-2460686034-10568566041753944064-2789274542-10057-A-0-1_1200.m3u8"));
        data.add(new TvModel("徐老师LOL故事", "http://aldirect.hls.huya.com/huyalive/28466698-2689658976-11551997339312848896-2789274534-10057-A-0-1_1200.m3u8"));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public SupetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType, SupetRecyclerAdapter adapter) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_settings_layout, parent, false);
        return new SupetRecyclerViewHolder(itemView, adapter);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(SupetRecyclerViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        TvModel model = data.get(position);
        name.setText(model.getName());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UINavgation.startLivePlayerActivity(getContext(), model.getUrl());
            }
        });

    }

}