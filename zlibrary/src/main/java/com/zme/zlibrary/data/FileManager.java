package com.zme.zlibrary.data;

import android.os.Environment;
import android.os.StatFs;

import com.zme.zlibrary.utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Zhiyahan
 * @ClassName: FileManager
 * @Description: TODO 文件管理、创建文件，删除文件、保存到文件等操作
 * @date 2015年9月25日 下午2:56:25
 */
public class FileManager {

  private static volatile FileManager fileManager;
  private static int oneMB = 1 * 1024 * 1024;//1MB
  private static String ROOT_FOLDER = "ZME";//APP总目录
  private static String ERROR_FILE_FOLDER = "ErrorLog";//错误文件的存放目录
  private static String DOWNLOAD_FOLDER = "Download";//下载文件的文件夹目录
  private static String TEMP_FOLDER = "Temp";//临时存放目录
  private static String PICTURE = "Picture";//图片存放目录
  private static String RECORD = "Record";//语音存放目录
  private static String VIDEO = "Video";//语音存放目录


  public static FileManager getInstance() {

    if (fileManager == null) {
      synchronized (FileManager.class) {
        if (fileManager == null) {
          fileManager = new FileManager();
        }
      }
    }
    return fileManager;
  }

  /**
   * @return {@link File}
   * @Title: createRootFiles
   * @Description: TODO 创建根目录文件
   */
  private File createRootFiles() {
    File file = null;
    try {
      //在System权限中，这个方法并不可行
      file = new File(Environment.getExternalStorageDirectory(), ROOT_FOLDER);
      if (!file.exists()) {
        file.mkdirs();
      }
      LogUtils.i("app根路径==" + file);
    } catch (Exception e) {
      // TODO: handle exception
      LogUtils.i("Exception" + e.getMessage());
    }
    return file;
  }


  /**
   * @param fileName 文件名
   * @return {@link File}
   * @Title: createFile
   * @Description: TODO 在根目录下 创建文件夹
   */
  private File createFile(String fileName) {
    // 创建一个文件
    File dir = new File(createRootFiles(), fileName);
    if (!dir.exists()) {
      dir.mkdirs();
    }
    LogUtils.i("createFile==" + dir.toString());
    return dir;
  }

  /**
   * 项目根目录下建立图片目录
   *
   * @return Picture Folder
   */
  public File createPictureFolder() {
    return createFile(PICTURE);
  }

  /**
   * 项目根目录下建立视频目录
   *
   * @return Video Folder
   */
  public File createVideoFolder() {
    return createFile(VIDEO);
  }

  /**
   * 项目根目录下建立临时目录
   *
   * @return Temp Folder
   */
  public File createTempFolder() {
    return createFile(TEMP_FOLDER);
  }


  /**
   * 项目根目录下建立语音目录
   *
   * @return record Folder
   */
  public File createRecordFolder() {
    return createFile(RECORD);
  }


  /**
   * 项目根目录下建立下载目录
   *
   * @return Download Folder
   */
  public File createDownLoadFolder() {
    return createFile(DOWNLOAD_FOLDER);
  }

  /**
   * 项目根目录下建立错误日志目录
   *
   * @return error log  Folder
   */
  public File createErrorLogFolder() {
    return createFile(ERROR_FILE_FOLDER);
  }

  /**
   * @param source {@link String} 字符串内容
   * @param filePath {@link String} 文件保存的路径
   * @param fileName {@link String} 保存的文件名
   * @return if return true save success,or return false;
   * @Title: saveFile
   * @Description: TODO  把String内容以.txt文件类型保存到SD卡的指定目录
   */
  public boolean saveFile(String source, String filePath, String fileName) {
    // TODO Auto-generated method stub
    File stringFile = null;
    try {
      stringFile = new File(filePath, fileName);
      if (!stringFile.exists()) {
        stringFile.createNewFile();
      }
      FileOutputStream out = new FileOutputStream(stringFile);
      out.write(source.getBytes());
      out.flush();
      out.close();
      return true;
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  /**
   * @param filePath {@link String}文件存放的路径
   * @param fileName {@link String} 文件名
   * @return {@link String}
   * @Title: readFile
   * @Description: TODO 读取SD卡的文件 转化成String
   */
  public static String readFile(String filePath, String fileName) {
    // TODO Auto-generated method stub
    int a = 0;
    StringBuffer sb = null;
    File stringFile = null;
    try {
      sb = new StringBuffer();
      stringFile = new File(filePath, fileName);
      if (!stringFile.exists()) {
        //文件不存在
        return null;
      }
      FileInputStream in = new FileInputStream(stringFile);
      while ((a = in.read()) != -1) {
        sb.append((char) a);
      }
      in.close();
      return sb.toString();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }

  }

  /**
   * @param folderPath 文件夹名
   * @return {@link Boolean}
   * @Title: deleteFolderFile
   * @Description: TODO  删除sd卡的指定的文件夹以及文件夹中的文件
   */
  public Boolean deleteFolder(String folderPath) {
    File file = new File(folderPath);
    if (file == null || !file.exists() || file.isFile()) {
      return false;
    }
    //删除文件夹的文件
    for (File files : file.listFiles()) {
      if (files.isFile()) {
        files.delete();
      } else if (files.isDirectory()) {
        deleteFolder(files.toString());// 递归
      }
    }
    //删除文件夹
    file.delete();
    return true;
  }

  /**
   * @param filePath 文件的路径
   * @return boolean
   * @Title: deleteFile
   * @Description: TODO  删除指定文件夹里的单个文件
   */
  public static boolean deleteFile(String filePath) {
    File file = new File(filePath);
    if (file == null || !file.exists() || file.isDirectory()) {
      return false;
    }
    //删除文件
    file.delete();
    return true;
  }

  /**
   * @return boolean
   * @Title: isExternalStorageAvailable
   * @Description: TODO 判断存储空间是否可用
   */
  public static boolean isExternalStorageAvailable() {
    return Environment.getExternalStorageState().equals(
        Environment.MEDIA_MOUNTED);
  }


  /**
   * @param filePath 文件的路径
   * @return boolean
   * @Title: isFileExist
   * @Description: TODO  判断某个文件夹下的某个文件是否存在
   */
  public static boolean isFileExist(String filePath) {
    File file = new File(filePath);
    if (file.exists() && !file.isDirectory()) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * @return long
   * @Title: getTotalExternalStorageSize
   * @Description: TODO  获取SD卡总容量
   */
  public long getTotalExternalStorageSize() {
    //空间是否可用
    if (!isExternalStorageAvailable()) {
      return -1;
    }
    File path = Environment.getExternalStorageDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize = stat.getBlockSize();
    long totalBlocks = stat.getBlockCount();
    return totalBlocks * blockSize / oneMB;

  }


  /**
   * @return long
   * @Title: getAvailableExternalStorageSize
   * @Description: TODO  获得外部SD卡可用空间
   */
  public long getAvailableExternalStorageSize() {
    if (!isExternalStorageAvailable()) {
      return -1;
    }
    File file = Environment.getExternalStorageDirectory();
    StatFs statFs = new StatFs(file.getPath());
    long blockSize = statFs.getBlockSize();
    long availableBlocks = statFs.getAvailableBlocks();
    return blockSize * availableBlocks / oneMB;
  }


  /**
   * @return long
   * @Title: getAvailableInternalStorageSize
   * @Description: TODO  获取内部存储可用空间
   */
  public long getAvailableInternalStorageSize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize = stat.getBlockSize();
    long availableBlocks = stat.getAvailableBlocks();
    return availableBlocks * blockSize / oneMB;
  }

  /**
   * @Title: getTotalInternalStorageSize
   * @Description: TODO 获取总内存空间
   */
  public static long getTotalInternalStorageSize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize = stat.getBlockSize();
    long totalBlocks = stat.getBlockCount();
    return totalBlocks * blockSize / oneMB;
  }


}
