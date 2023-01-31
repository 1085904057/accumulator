package com.spring.accumulator.controller;


import com.spring.accumulator.dao.PoiMapper;
import com.spring.accumulator.model.vo.VectorTile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Api(tags = "动态矢量瓦片请求")
@Slf4j
@RestController
@RequestMapping("/vector-tile")
public class VectorTileController {

    @ApiOperation(value = "动态矢量切片请求")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "z", value = "缩放等级", required = true),
            @ApiImplicitParam(name = "y", value = "瓦片行号", required = true),
            @ApiImplicitParam(name = "x", value = "瓦片列号", required = true)
    })
    @GetMapping("/tile/{z}/{y}/{x}.pbf")
    public void listPerson(@PathVariable Integer z,
                           @PathVariable Integer y,
                           @PathVariable Integer x,
                           HttpServletResponse response) {

        try {
            response.setContentType("application/x-protobuf");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码
            String encodedFileName = URLEncoder.encode(x.toString(), StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=utf-8''" + encodedFileName + ".pbf");
            VectorTile vectorTile = poiMapper.selectVectorTile(z, x, y);
            response.getOutputStream().write(vectorTile.getMvt());
            System.out.println(z + "-" + y + "-" + x + ":" + vectorTile.getMvt().length);
        } catch (Exception e) {
            // 重置response
            log.error("文件下载失败" + e.getMessage());
            throw new RuntimeException("下载文件失败", e);
        }
    }

    @Resource
    private PoiMapper poiMapper;
}
