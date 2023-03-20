package com.spring.accumulator.dao;

import com.spring.accumulator.entity.PoiPO;
import com.spring.accumulator.model.vo.VectorTile;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class PoiMapperTest {

    @Resource
    private PoiMapper poiMapper;

    @Test
    public void testInsert() {
        GeometryFactory factory = new GeometryFactory();
        PoiPO aoiPo = new PoiPO();
        aoiPo.setId(1);
        aoiPo.setName("兰州牛肉面");
        aoiPo.setAddress("北京市大兴区亦庄镇");
        aoiPo.setGeom(factory.createPoint(new Coordinate(116.3, 25.7)));
        poiMapper.insert(aoiPo);
        System.out.println(aoiPo.getId());
    }

    @Test
    public void testQuery() {
        PoiPO poiPO = poiMapper.selectGeom(1);
        System.out.println(poiPO);
    }

    @Test
    public void testVectorTile() throws IOException {
        VectorTile vectorTile = poiMapper.selectVectorTile(1, 1, 0);
        System.out.println(vectorTile);
        File file = new File("E://tile/test.pbf");
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(vectorTile.getMvt());
        fos.flush();
        fos.close();
    }

}