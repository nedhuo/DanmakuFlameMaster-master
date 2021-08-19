package com.sample;

import static java.lang.Float.parseFloat;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.SpecialDanmaku;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.android.AndroidFileSource;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

public class LongChatDanmuParser extends BaseDanmakuParser {
    protected float mDisplayScaleX;
    protected float mDisplayScaleY;
    int index = 0;
    private Danmakus danmakus; //弹幕库
    private BaseDanmaku item; //弹幕项

    @Override
    protected IDanmakus parse() {

        int position = 0;
        function1();
        while (position < 500) {
            function2();
            /*
             * 封装弹幕数据
             * */
            function3();

            function4();

            position++;
        }
        return danmakus;

    }

    private void function1() {
        danmakus = new Danmakus();
    }

    private void function2() {
        // <d p="23.826000213623,1,25,16777215,1422201084,0,057075e9,757076900">我从未见过如此厚颜无耻之猴</d>
        // 0:时间(弹幕出现时间)
        // 1:类型(1从右至左滚动弹幕|6从左至右滚动弹幕|5顶端固定弹幕|4底端固定弹幕|7高级弹幕|8脚本弹幕)
        // 2:字号
        // 3:颜色
        // 4:时间戳 ?
        // 5:弹幕池id
        // 6:用户hash
        // 7:弹幕id
        // parse p value to danmaku


        long time = 0l; // 出现时间
        int type = 1; //弹幕类型 默认（从右向左滑动）
        float textSize = 25F; //字体大小
        int textColor = Color.parseColor("#000000"); //字体颜色
        // int poolType = parseInteger(values[5]); // 弹幕池类型（忽略
        item = mContext.mDanmakuFactory.createDanmaku(type, mContext); //创建弹幕
        if (item != null) {
            /*
             * 计算字符长度，获取长度所占用的出场时间 + 随机时间（可以根据弹幕数量来计算，也可以获取一个小的随机数）
             * */
            Random random = new Random();
            int i = random.nextInt(5);
            item.setTime(time + (i * 100) + (index * 600)); //设置出现时间
            item.textSize = textSize * (mDispDensity - 0.6f); //字体大小
            item.textColor = textColor; //字体颜色
            item.textShadowColor = textColor <= Color.BLACK ? Color.WHITE : Color.BLACK;
        }


    }


    /**
     * characters
     */
    private void function3() {
        if (item != null) {
            item.text = "asdasdasdas";

            //item.lines = lines;//

            item.index = index++;

            // initial specail danmaku data
            //           String text = String.valueOf(item.text).trim();
//            if (item.getType() == BaseDanmaku.TYPE_SPECIAL && text.startsWith("[")
//                    && text.endsWith("]")) {
//                //text = text.substring(1, text.length() - 1);
//                String[] textArr = null;//text.split(",", -1);
//                try {
//                    JSONArray jsonArray = new JSONArray(text);
//                    textArr = new String[jsonArray.length()];
//                    for (int i = 0; i < textArr.length; i++) {
//                        textArr[i] = jsonArray.getString(i);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                if (textArr == null || textArr.length < 5 || TextUtils.isEmpty(textArr[4])) {
//                    item = null;
//                    return;
//                }
//                DanmakuUtils.fillText(item, textArr[4]);
//                float beginX = parseFloat(textArr[0]);
//                float beginY = parseFloat(textArr[1]);
//                float endX = beginX;
//                float endY = beginY;
//                String[] alphaArr = textArr[2].split("-");
//                int beginAlpha = (int) (AlphaValue.MAX * parseFloat(alphaArr[0]));
//                int endAlpha = beginAlpha;
//                if (alphaArr.length > 1) {
//                    endAlpha = (int) (AlphaValue.MAX * parseFloat(alphaArr[1]));
//                }
//                long alphaDuraion = (long) (parseFloat(textArr[3]) * 1000);
//                long translationDuration = alphaDuraion;
//                long translationStartDelay = 0;
//                float rotateY = 0, rotateZ = 0;
//                if (textArr.length >= 7) {
//                    rotateZ = parseFloat(textArr[5]);
//                    rotateY = parseFloat(textArr[6]);
//                }
//                if (textArr.length >= 11) {
//                    endX = parseFloat(textArr[7]);
//                    endY = parseFloat(textArr[8]);
//                    if (!"".equals(textArr[9])) {
//                        translationDuration = parseInteger(textArr[9]);
//                    }
//                    if (!"".equals(textArr[10])) {
//                        translationStartDelay = (long) (parseFloat(textArr[10]));
//                    }
//                }
//                if (isPercentageNumber(textArr[0])) {
//                    beginX *= DanmakuFactory.BILI_PLAYER_WIDTH;
//                }
//                if (isPercentageNumber(textArr[1])) {
//                    beginY *= DanmakuFactory.BILI_PLAYER_HEIGHT;
//                }
//                if (textArr.length >= 8 && isPercentageNumber(textArr[7])) {
//                    endX *= DanmakuFactory.BILI_PLAYER_WIDTH;
//                }
//                if (textArr.length >= 9 && isPercentageNumber(textArr[8])) {
//                    endY *= DanmakuFactory.BILI_PLAYER_HEIGHT;
//                }
//                item.duration = new Duration(alphaDuraion);
//                item.rotationZ = rotateZ;
//                item.rotationY = rotateY;
//                mContext.mDanmakuFactory.fillTranslationData(item, beginX,
//                        beginY, endX, endY, translationDuration, translationStartDelay, mDispScaleX, mDispScaleY);
//                mContext.mDanmakuFactory.fillAlphaData(item, beginAlpha, endAlpha, alphaDuraion);
//
//                if (textArr.length >= 12) {
//                    // 是否有描边
//                    if (!TextUtils.isEmpty(textArr[11]) && TRUE_STRING.equalsIgnoreCase(textArr[11])) {
//                        item.textShadowColor = Color.TRANSPARENT;
//                    }
//                }
//                if (textArr.length >= 13) {
//                    //TODO 字体 textArr[12]
//                }
//                if (textArr.length >= 14) {
//                    // Linear.easeIn or Quadratic.easeOut
//                    ((SpecialDanmaku) item).isQuadraticEaseOut = ("0".equals(textArr[13]));
//                }
//                if (textArr.length >= 15) {
//                    // 路径数据
//                    if (!"".equals(textArr[14])) {
//                        String motionPathString = textArr[14].substring(1);
//                        if (!TextUtils.isEmpty(motionPathString)) {
//                            String[] pointStrArray = motionPathString.split("L");
//                            if (pointStrArray.length > 0) {
//                                float[][] points = new float[pointStrArray.length][2];
//                                for (int i = 0; i < pointStrArray.length; i++) {
//                                    String[] pointArray = pointStrArray[i].split(",");
//                                    if (pointArray.length >= 2) {
//                                        points[i][0] = parseFloat(pointArray[0]);
//                                        points[i][1] = parseFloat(pointArray[1]);
//                                    }
//                                }
//                                mContext.mDanmakuFactory.fillLinePathData(item, points, mDispScaleX,
//                                        mDispScaleY);
//                            }
//                        }
//                    }
//                }
//            }

        }
    }

    /**
     * end
     */
    private void function4() {
        if (item != null && item.text != null) {
            if (item.duration != null) {

                item.setTimer(mTimer); //TODO 这是一个什么时间
                item.flags = mContext.mGlobalFlagValues; //类似更新弹幕的旋转、偏移、可见等
                Object lock = danmakus.obtainSynchronizer();
                synchronized (lock) {
                    danmakus.addItem(item);
                }
            }
        }
        item = null;
    }


    private boolean isPercentageNumber(String number) {
        //return number >= 0f && number <= 1f;
        return number != null && number.contains(".");
    }

    private float parseFloat(String floatStr) {
        try {
            return Float.parseFloat(floatStr);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    private int parseInteger(String intStr) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private long parseLong(String longStr) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    @Override
    public BaseDanmakuParser setDisplayer(IDisplayer disp) {
        super.setDisplayer(disp);
        mDisplayScaleX = mDispWidth / DanmakuFactory.BILI_PLAYER_WIDTH;
        mDisplayScaleY = mDispHeight / DanmakuFactory.BILI_PLAYER_HEIGHT;
        return this;
    }
}
