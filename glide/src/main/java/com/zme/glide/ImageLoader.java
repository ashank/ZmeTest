package com.zme.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName    : ImageLoader
 *
 * @author : Zhiyahan
 * @date        : 2019/7/26 17:19
 * Description : 加载器
 *
 *
 * //设置空白图片
 *
 * //设置加载错误后的图片
 *
 * //设置圆形，或者 矩形 其他形状
 *
 * //设置大小
 *
 * //设置换车策略
 *
 * //设置
 *
 *
 *
 */
@SuppressWarnings("unused")
public class ImageLoader {

    private static final String TAG = "ImageLoader";
    private Context mContext;
    private String url;
    private RequestOptions mRequestOptions;
    private final static ImageLoader sImageLoader = new ImageLoader();
    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private ImageLoader() {
    }

    public static ImageLoader with() {
        return sImageLoader;
    }

    /**
     * 清除Glide在APP中的缓存, 包括在内存中的缓存以及本地缓存
     *
     * @param context 上下文
     */
    public boolean clearCache(Context context) {
        try {
            //需要在主线程里执行
            Glide.get(context.getApplicationContext()).clearMemory();
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //不能在主线程里执行
                    Glide.get(mContext.getApplicationContext()).clearDiskCache();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }




    @SuppressWarnings("unused")
    public static Builder with(Context context) {
        return  new ImageLoader.Builder(context);
    }

    public static class Builder{
        private Context mContext;
        private String url;
        private int placeholder;
        private int errorPlaceholder;
        private float thumbnail;
        private  boolean isSkipMemoryCache;
        private int width;
        private int height;
        private RequestListener mRequestListener;
        private   Transformation<Bitmap>[] transformations;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder load(String url){
            this.url = url;
            return this;
        }

        public Builder placeholder(int placeholder){
            this.placeholder = placeholder;
            return this;
        }

        public Builder error(int errorPlaceholder){
            this.errorPlaceholder = errorPlaceholder;
            return this;
        }

        public Builder requestListener(RequestListener requestListener){
            this.mRequestListener = requestListener;
            return this;
        }

        public Builder thumbnail(float thumbnail){
            this.thumbnail = thumbnail;
            return this;
        }

        public Builder override(int width,int height){
            this.width = width;
            this.height = height;
            return  this;
        }

        public Builder skipMemoryCache(boolean isSkipMemoryCache){
            this.isSkipMemoryCache = isSkipMemoryCache;
            return  this;
        }


        public Builder circleTran(){


            return  this;
        }


        public Builder transform(@NonNull Transformation<Bitmap>... transformations){
            this.transformations = transformations;
            return this;
        }


        public ImageLoader into(ImageView imageView) {
            return new ImageLoader(imageView,this);
        }


    }


    private ImageLoader(ImageView imageView,Builder builder) {
        url = builder.url;
        mContext = builder.mContext;
        mRequestOptions = new RequestOptions();
        if ( builder.placeholder!=0){
            mRequestOptions = mRequestOptions.placeholder(builder.placeholder);
        }

        if (builder.errorPlaceholder!=0){
            mRequestOptions = mRequestOptions.error(builder.errorPlaceholder);
        }

        if (builder.width>0 && builder.height>0){
            mRequestOptions = mRequestOptions.override(builder.width, builder.height);
        }

        if (builder.transformations!= null && builder.transformations.length>0){
            mRequestOptions = mRequestOptions.transform(builder.transformations);
        }

        mRequestOptions = mRequestOptions
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        RequestBuilder requestBuilder = Glide.with(mContext)
                .load(url)
                .apply(mRequestOptions);

        if (builder.thumbnail>0){
            requestBuilder = requestBuilder.thumbnail(builder.thumbnail);
        }

        if (builder.mRequestListener!=null){
            requestBuilder = requestBuilder.listener(builder.mRequestListener);
        }


        requestBuilder.into(imageView);
    }
}