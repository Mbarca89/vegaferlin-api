package com.mbarca.VegaFerlin.domain;

import java.util.List;

public class Images {
    byte [] fullImage;
    byte [] thumbnail;
    List<String> paths;

    public Images() {
    }

    public Images(byte[] fullImage, byte[] thumbnail) {
        this.fullImage = fullImage;
        this.thumbnail = thumbnail;
    }

    public Images(byte[] fullImage, byte[] thumbnail, List<String> paths) {
        this.fullImage = fullImage;
        this.thumbnail = thumbnail;
        this.paths = paths;
    }

    public byte[] getFullImage() {
        return fullImage;
    }

    public void setFullImage(byte[] fullImage) {
        this.fullImage = fullImage;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> path) {
        this.paths = paths;
    }
}
