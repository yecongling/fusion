package cn.net.fusion.framework.controller;

import cn.net.fusion.framework.core.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName FileUploadController
 * @Description 统一的文件上传入口
 * @Author ycl
 * @Date 2025/1/17 09:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 文件上传接口
     *
     * @param files 文件(支持多文件)
     * @return 结果
     */
    @PostMapping("/upload")
    public Response<String> uploadFile(@RequestParam("files") MultipartFile[] files) {
        return null;
    }

    /**
     * 文件分片上传
     *
     * @param file 文件
     * @param fileName 文件名
     * @param chunkNumber 分片数
     * @param totalChunks 总共分片数
     * @return 结果
     */
    @PostMapping("/uploadChunk")
    public Response<String> uploadFileChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("totalChunks") int totalChunks) {
        return null;
    }

    /**
     * 检查文件的上传状态，用于文件的断点续传
     *
     * @param fileName 文件名
     * @param totalChunks 总共的分片数
     * @return 结果
     */
    @GetMapping("/checkUploadStatus")
    public Response<Map<Integer, Boolean>> checkUploadStatus(@RequestParam("fileName") String fileName, @RequestParam("totalChunks") int totalChunks) {
        return null;
    }
}
