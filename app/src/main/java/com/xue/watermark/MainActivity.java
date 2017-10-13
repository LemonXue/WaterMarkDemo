package com.xue.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import static com.xue.watermark.WaterImageUtil.dp2px;

public class MainActivity extends AppCompatActivity {
    private ImageView mSourImage;
    private ImageView mWartermarkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSourImage = (ImageView) findViewById(R.id.sour_pic);
        mWartermarkImage = (ImageView) findViewById(R.id.wartermark_pic);
        initWaterImg();
    }
    /**
     * 添加水印的方法有3个步骤：
     * 1、载入原始图片
     * 2、载入水印图片
     * 3、保存带有水印的图片
     * 实现的原理就是：获取原始图片的宽高，然后，新建一个同样宽高的bitmap，将这个新的bitmap作为画布
     * 接着，就在这个画布上面画原图，画水印图片，有文字就接着画文字。
     */
    private void initWaterImg() {

        Bitmap sourBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat1);
        mSourImage.setImageBitmap(sourBitmap);

        Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //设置水印图片在左上角、右下角、右上角、左下角、中间
        Bitmap watermarkBitmap = WaterImageUtil.createWaterMaskCenter(sourBitmap, waterBitmap);
        watermarkBitmap = WaterImageUtil.createWaterMaskLeftBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterImageUtil.createWaterMaskRightBottom(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterImageUtil.createWaterMaskLeftTop(this, watermarkBitmap, waterBitmap, 0, 0);
        watermarkBitmap = WaterImageUtil.createWaterMaskRightTop(this, watermarkBitmap, waterBitmap, 0, 0);
        //给图片添加文字到左上角、右下角、右上角、左下角、中间
        Bitmap textBitmap = WaterImageUtil.drawTextToLeftTop(this, watermarkBitmap, "左上角", 16, Color.RED, 0, 0);
        textBitmap = WaterImageUtil.drawTextToRightBottom(this, textBitmap, "右下角", 16, Color.RED, 0, 0);
        textBitmap = WaterImageUtil.drawTextToRightTop(this, textBitmap, "右上角", 16, Color.RED, 0, 0);
        textBitmap = WaterImageUtil.drawTextToLeftBottom(this, textBitmap, "左下角", 16, Color.RED, 0, 0);
        textBitmap = WaterImageUtil.drawTextToCenter(this, textBitmap, "中间", 16, Color.RED);
        //设置最终绘制完成的图片
        mWartermarkImage.setImageBitmap(textBitmap);
    }


}
