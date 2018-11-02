package com.example.jh.rxhapp.camera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;

public class CamParaUtil {
    private static final String TAG = "yanzi";
    private CameraSizeComparator sizeComparator = new CameraSizeComparator();
    private static CamParaUtil myCamPara = null;

    private CamParaUtil() {

    }

    public static CamParaUtil getInstance() {
        if (myCamPara == null) {
            myCamPara = new CamParaUtil();
            return myCamPara;
        } else {
            return myCamPara;
        }
    }

    public Size getPropPreviewSize(List<Camera.Size> list, float th, int minWidth) {
        Collections.sort(list, sizeComparator);

        int i = 0;
        for (Size s : list) {
            if ((s.width >= minWidth) && equalRate(s, th)) {
                Log.i(TAG, "PreviewSize:w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }
        if (i == list.size()) {
            i = 0;//如果没找到，就选最小的size
        }
        return list.get(i);
    }

    public Size getPropPictureSize(List<Camera.Size> list, float th, int minWidth) {
        Collections.sort(list, sizeComparator);

        int i = 0;
        for (Size s : list) {
            if ((s.width >= minWidth) && equalRate(s, th)) {
                Log.i(TAG, "PictureSize : w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }
        if (i == list.size()) {
            i = 0;//如果没找到，就选最小的size
        }
        return list.get(i);
    }

    public boolean equalRate(Size s, float rate) {
        float r = (float) (s.width) / (float) (s.height);
        if (Math.abs(r - rate) <= 0.03) {
            return true;
        } else {
            return false;
        }
    }

    public class CameraSizeComparator implements Comparator<Camera.Size> {
        public int compare(Size lhs, Size rhs) {
            // TODO Auto-generated method stub
            if (lhs.width == rhs.width) {
                return 0;
            } else if (lhs.width > rhs.width) {
                return 1;
            } else {
                return -1;
            }
        }

    }

    /**
     * 打印支持的previewSizes
     *
     * @param params
     */
    public void printSupportPreviewSize(Camera.Parameters params) {
        List<Size> previewSizes = params.getSupportedPreviewSizes();
        for (int i = 0; i < previewSizes.size(); i++) {
            Size size = previewSizes.get(i);
            Log.i(TAG, "previewSizes:width = " + size.width + " height = " + size.height);
        }

    }

    /**
     * 打印支持的pictureSizes
     *
     * @param params
     */
    public void printSupportPictureSize(Camera.Parameters params) {
        List<Size> pictureSizes = params.getSupportedPictureSizes();
        for (int i = 0; i < pictureSizes.size(); i++) {
            Size size = pictureSizes.get(i);
            Log.i(TAG, "pictureSizes:width = " + size.width
                    + " height = " + size.height);
        }
    }

    /**
     * 打印支持的聚焦模式
     *
     * @param params
     */
    public void printSupportFocusMode(Camera.Parameters params) {
        List<String> focusModes = params.getSupportedFocusModes();
        for (String mode : focusModes) {
            Log.i(TAG, "focusModes--" + mode);
        }
    }

    /**
     * 最小预览界面的分辨率
     */
    private static final int MIN_PREVIEW_PIXELS = 480 * 320;
    /**
     * 最大宽高比差
     */
    private static final double MAX_ASPECT_DISTORTION = 0.15;

    /**
     * 根据图片展示区域 选择 相机分辨率
     * TODO 摄像头旋转是否需要考虑
     *
     * @param parameters       相机配置
     * @param screenResolution 图片展示区域
     *
     * @return
     */
    public static Point getBestPreview(Camera.Parameters parameters, Point screenResolution) {
        List<Size> rawSupportedSizes = parameters.getSupportedPreviewSizes();
        Size defaultSize = parameters.getPreviewSize();
        if (rawSupportedSizes == null) {
            return new Point(defaultSize.width, defaultSize.height);
        } else {
            // 获取摄像头支持的分辨率
            List<Size> cameraSupportedSizes = new ArrayList(rawSupportedSizes);
            // 按照分辨率从大到小排序
            Collections.sort(cameraSupportedSizes, new Comparator<Size>() {
                public int compare(Size a, Size b) {
                    int aPixels = a.height * a.width;
                    int bPixels = b.height * b.width;
                    if (bPixels < aPixels) {
                        return -1;
                    } else {
                        return bPixels > aPixels ? 1 : 0;
                    }
                }
            });
            // 屏幕分辨率宽高比不相等的情况下
            double screenRatio =
                    screenResolution.x > screenResolution.y
                            ? (double) screenResolution.x / (double) screenResolution.y :
                            (double) screenResolution.y / (double) screenResolution.x;
            Iterator iterator = cameraSupportedSizes.iterator();
            while (iterator.hasNext()) {
                Size supportedPreviewSize = (Size) iterator.next();
                int width = supportedPreviewSize.width;
                int height = supportedPreviewSize.height;
                // 移除低于下限的分辨率
                if (width * height < MIN_PREVIEW_PIXELS) {
                    iterator.remove();
                    continue;
                } else {
                    // 在相机像素分辨率宽高比
                    double cameraRatio = supportedPreviewSize.width > supportedPreviewSize.height
                            ? (double) supportedPreviewSize.width / (double) supportedPreviewSize.height :
                            (double) supportedPreviewSize.height / (double) supportedPreviewSize.width;
                    double distortion = Math.abs(cameraRatio - screenRatio);
                    // 移除宽高比差距大的分辨率
                    if (distortion > MAX_ASPECT_DISTORTION) {
                        iterator.remove();
                        continue;
                    }
                    // 找到与屏幕分辨率完全匹配的预览界面分辨率直接返回
                    if (width == screenResolution.x && height == screenResolution.y) {
                        return new Point(width, height);
                    }
                }
            }
            // 如果没有找到合适的，并且还有候选的像素，则设置其中最大比例的，对于配置比较低的机器不太合适
            int index = 0;
            if (cameraSupportedSizes.size() > index) {
                Size largestPreview = cameraSupportedSizes.get(index);
                Point largestSize = new Point(largestPreview.width, largestPreview.height);
                return largestSize;
            }
            // 没有找到合适的，就返回默认的
            return new Point(defaultSize.width, defaultSize.height);
        }
    }
}

