package com.ssic.xinmin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.ssic.xinmin.dto.UploadDto;
import com.ssic.xinmin.utils.DateUtils;
import com.ssic.xinmin.utils.FileUtils;
import com.ssic.xinmin.utils.LogWriter;
import com.ssic.xinmin.utils.PropertiesUtils;
import com.ssic.xinmin.utils.constants.DataStatus;
import com.ssic.xinmin.utils.model.Response;

/**
 * 
 * 此类描述的是：用于上传对外接口
 * 
 * @author: cwftalus@163.com
 * @version: 2015年4月1日 上午11:40:00
 */

@Controller
@RequestMapping(value = "/api/")
public class UploadController {
	protected static final Log logger = LogFactory.getLog(UploadController.class);

	@RequestMapping(value = "/upload.do")
//	@ResponseBody
	private void upload(@RequestParam("file") MultipartFile myfile,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		Response<UploadDto> result = new Response<UploadDto>();
		UploadDto uploadDto = new UploadDto();
		if (!myfile.isEmpty()) {
			long fileSize = printFileInfo(myfile);//判断图片大小 超过1M不允许上传
			if(fileSize > DataStatus._FILESIZE_){
				result.setStatus(DataStatus.HTTP_FAILE);
				result.setMessage("上传图片大小不允许超过1M");
//				return result;
			}else{
				try {
					uploadDto.setUrl(uploadImage(myfile, request));
					result.setData(uploadDto);
					result.setStatus(DataStatus.HTTP_SUCCESS);
					result.setMessage("图片上传成功");
				} catch (IOException e) {
					result.setData(uploadDto);
					result.setStatus(DataStatus.HTTP_FAILE);
					result.setMessage("图片上传失败");
					logger.debug(LogWriter.getStackMsg(e.getStackTrace()));
				}
			}
			
			
		}
		
		PrintWriter writer = response.getWriter();
		Gson gson = new Gson();
		writer.print(gson.toJson(result));		
		
		writer.close();		
//		return result;
	}

	private String uploadImage(MultipartFile myfile, HttpServletRequest request)
			throws IOException {
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		String context = "/upload";
		String realPath = PropertiesUtils.getProperty("upload.url");
		String fileDe = DateUtils.format(new Date(), DateUtils.YMD);
		String fileName = FileUtils.getUploadFileName(myfile.getOriginalFilename());
		logger.info("filePaht :"+realPath + "/" +fileDe + fileName);
		File file = new File(realPath + "/" +context+"/"+fileDe);
        //如果文件夹不存在则创建    
        if(!file.exists() && !file.isDirectory()) {
            file.mkdir();    
        }
        
		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath + "/" +context+"/"+fileDe , fileName));
		return (context + "/" + fileDe + "/" + fileName);
	}

	private long printFileInfo(MultipartFile myfile) {
		logger.info("文件长度: " + myfile.getSize());
		logger.info("文件类型: " + myfile.getContentType());
		logger.info("文件名称: " + myfile.getName());
		logger.info("文件原名: " + myfile.getOriginalFilename());
		logger.info("========================================");
		return myfile.getSize();
	}
}
