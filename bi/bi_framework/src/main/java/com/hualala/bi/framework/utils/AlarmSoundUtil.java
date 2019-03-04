package com.hualala.bi.framework.utils;//package com.hualala.mobilebox.utils;
//
//import android.content.Context;
//import android.media.AudioManager;
//import android.media.SoundPool;
//import android.util.SparseIntArray;
//
//import com.hualala.libutils.MBContext;
//
//public class AlarmSoundUtil {
//    public static final int KEY_input_your_number = 101;
//    public static final int KEY_selectgekou = 103;
//    public static final int KEY_selectchongzhi = 104;
//    public static final int KEY_please_input_pickup_password = 105;
//    public static final int KEY_sao = 107;
//    public static final int KEY_closebox = 108;
//    public static final int KEY_please_input_consignee_phone = 109;
//    public static final int KEY_open_box_error = 110;
//    public static final int KEY_deliver_closebox = 111;
//    public static final int KEY_finishe = 112;
//    public static final int KEY_CHONGZHI_JIN_E = 113;
//    public static final int KEY_TOU_BI = 114;
//    public static final int KEY_TOU_BI_2 = 115;
//    public static final int KEY_DU = 118;
//    //请输入取件密码
//    public static final int KEY_please_input_waybill = 116;
//    //请输入手机号码和动态密码
//    public static final int KEY_please_input_phone_and_dynamicpassword = 117;
//
//    /**
//     * 数字语音的开始位置：后续位置即 KEY_NUMBER_START + n ,n = 0,1,2,3...
//     */
//    public static final int KEY_NUMBER_0 = 201509150;
//    public static final int KEY_NUMBER_1 = 201509151;
//    public static final int KEY_NUMBER_2 = 201509152;
//    public static final int KEY_NUMBER_3 = 201509153;
//    public static final int KEY_NUMBER_4 = 201509154;
//    public static final int KEY_NUMBER_5 = 201509155;
//    public static final int KEY_NUMBER_6 = 201509156;
//    public static final int KEY_NUMBER_7 = 201509157;
//    public static final int KEY_NUMBER_8 = 201509158;
//    public static final int KEY_NUMBER_9 = 201509159;
//
//    private static AlarmSoundUtil mInstance;
//    private SoundPool mSoundPool;
//    private SparseIntArray soundMap;
//
//    public static AlarmSoundUtil getInstance() {
//        if (mInstance == null) {
//            mInstance = new AlarmSoundUtil(MBContext.getInstance());
//        }
//        return mInstance;
//    }
//
//    private AlarmSoundUtil(Context context) {
//        mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 5);
//        soundMap = new SparseIntArray();
//        load(context);
//    }
//
//    private void load(Context context) {
////
////        soundMap.put(KEY_input_your_number, soundPool.load(context, R.raw.input_your_number, 1)); // 请输入你的手机号码及短信验证码
////        //soundMap.put(102, soundPool.load(context,
////        // R.raw.toudifinish, 1));// 投递已完成
////        soundMap.put(KEY_selectgekou, soundPool.load(context, R.raw.selectgekou, 1));// 请选择可用箱体
////        soundMap.put(KEY_selectchongzhi, soundPool.load(context, R.raw.selectchongzhi, 1));// 请选择充值方式
////        soundMap.put(KEY_please_input_pickup_password, soundPool.load(context, R.raw.please_input_pickup_password, 1));// 请输入取件密码
////        //soundMap.put(106, soundPool.load(context,
////        // R.raw.qujianfinish, 1));//取件已完成
////        soundMap.put(KEY_sao, soundPool.load(context, R.raw.sao, 1));// 投递，请扫描单号并输入收件人手机号码
////        soundMap.put(KEY_closebox, soundPool.load(context, R.raw.closebox, 1));// 请关闭箱门
////        soundMap.put(KEY_please_input_consignee_phone, soundPool.load(context, R.raw.please_input_consignee_phone, 1));// 寄存，请输入收件人手机号码
////        soundMap.put(KEY_open_box_error, soundPool.load(context, R.raw.open_box_error, 1));// 打开箱体失败
////        soundMap.put(KEY_deliver_closebox, soundPool.load(context, R.raw.deliver_closebox, 1));// //请投递完成后关闭相关
////        soundMap.put(KEY_finishe, soundPool.load(context, R.raw.finishe, 1));// //箱门已开，取件后关闭箱门
////        soundMap.put(KEY_CHONGZHI_JIN_E, soundPool.load(context, R.raw.czje, 1));// //充值金额
////        soundMap.put(KEY_TOU_BI, soundPool.load(context, R.raw.tb, 1));// //投币
////        soundMap.put(KEY_TOU_BI_2, soundPool.load(context, R.raw.tb, 1));// //箱门已开,请投递，如果箱门未开，请取消投递
////        soundMap.put(KEY_please_input_waybill, soundPool.load(context, R.raw.please_input_waybill, 1));
////        soundMap.put(KEY_please_input_phone_and_dynamicpassword, soundPool.load(context, R.raw.please_input_phone_and_dynamicpassword, 1));
////        soundMap.put(KEY_DU, soundPool.load(context, R.raw.du, 1));
////
////        soundMap.put(KEY_NUMBER_0, soundPool.load(context, R.raw.number_0, 1));
////        soundMap.put(KEY_NUMBER_1, soundPool.load(context, R.raw.number_1, 1));
////        soundMap.put(KEY_NUMBER_2, soundPool.load(context, R.raw.number_2, 1));
////        soundMap.put(KEY_NUMBER_3, soundPool.load(context, R.raw.number_3, 1));
////        soundMap.put(KEY_NUMBER_4, soundPool.load(context, R.raw.number_4, 1));
////        soundMap.put(KEY_NUMBER_5, soundPool.load(context, R.raw.number_5, 1));
////        soundMap.put(KEY_NUMBER_6, soundPool.load(context, R.raw.number_6, 1));
////        soundMap.put(KEY_NUMBER_7, soundPool.load(context, R.raw.number_7, 1));
////        soundMap.put(KEY_NUMBER_8, soundPool.load(context, R.raw.number_8, 1));
////        soundMap.put(KEY_NUMBER_9, soundPool.load(context, R.raw.number_9, 1));
//    }
//
//    /**
//     * 音频资源ID 播放语音
//     *
//     * @param key 音频资源ID
//     */
//    public void play(int key) {
//        if (mSoundPool == null) return;
//        mSoundPool.play(soundMap.get(key), 1, 1, 0, 0, 1);
//    }
//
//    public void release() {
//        if (mSoundPool != null) {
//            mSoundPool.release();
//        }
//    }
//
//}