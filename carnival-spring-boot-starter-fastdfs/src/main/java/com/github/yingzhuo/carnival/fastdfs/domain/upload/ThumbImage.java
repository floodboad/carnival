/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.fastdfs.domain.upload;

import java.io.Serializable;

/**
 * 缩略图配置
 * <pre>
 *     支持三种配置
 *     1. 按默认配置
 *     2. 支持按比例缩放
 *     3. 按长宽缩放
 *     如果配置按比例缩放，则按比例计算
 *     如果没有配置按比例缩放，则按长宽缩放
 * </pre>
 *
 * @author tobato
 */
public class ThumbImage implements Serializable {

    /**
     * 按默认配置生成缩略图
     */
    private boolean defaultConfig = false;

    /**
     * 缩放长度
     */
    private int width;

    /**
     * 缩放高度
     */
    private int height;

    /**
     * 缩放比例
     */
    private double percent;

    /**
     * 按长宽缩放
     */
    public ThumbImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 按比例缩放
     */
    public ThumbImage(double percent) {
        this.percent = percent;
    }

    /**
     * 按默认配置生成
     */
    public ThumbImage() {
        this.defaultConfig = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPercent() {
        return percent;
    }

    public boolean isDefaultConfig() {
        return defaultConfig;
    }

    /**
     * 生成前缀如:_150x150
     */
    public String getPrefixName() {
        if (isDefaultConfig()) {
            return getPrefixNameBySize();
        } else if (percent != 0) {
            return getPrefixNameByPercent();
        } else {
            return getPrefixNameBySize();
        }
    }

    /**
     * 按缩放尺寸获取前缀
     */
    private String getPrefixNameBySize() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("_").append(width).append("x").append(height);
        return new String(buffer);
    }

    /**
     * 按缩放尺寸获取前缀
     */
    private String getPrefixNameByPercent() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("_").append(Math.round(100 * percent)).append("p_");
        return new String(buffer);
    }

    /**
     * 根据文件名获取缩略图路径
     */
    public String getThumbImagePath(String masterFilename) {
        StringBuilder buff = new StringBuilder(masterFilename);
        int index = buff.lastIndexOf(".");
        buff.insert(index, getPrefixName());
        return new String(buff);
    }

    /**
     * 设置默认缩放尺寸
     */
    public void setDefaultSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
