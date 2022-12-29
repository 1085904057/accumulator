package com.spring.accumulator.entity.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.postgis.PGgeometry;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Geometry字段和MyBatis类型转换器
 *
 * @param <T> 具体的几何类型
 * @author wangrubin1
 * @date 2022-08-06
 */
public abstract class AbstractGeometryTypeHandler<T extends Geometry> extends BaseTypeHandler<T> {

    /**
     * WKTReader非线程安全
     */
    private static final ThreadLocal<WKTReader> READER_POOL = ThreadLocal.withInitial(WKTReader::new);

    /**
     * WKTWriter非线程安全
     */
    private static final ThreadLocal<WKTWriter> WRITER_POOL = ThreadLocal.withInitial(WKTWriter::new);

    /**
     * 与数据库中几何列的空间坐标系保持一致，要不然写入会报错
     */
    private static final int SRID_IN_DB = 4326;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(WRITER_POOL.get().write(parameter));
        org.postgis.Geometry geometry = pGgeometry.getGeometry();
        geometry.setSrid(SRID_IN_DB);
        ps.setObject(i, pGgeometry);
    }

    @SneakyThrows
    @Override
    public T getNullableResult(ResultSet rs, String columnName) {
        PGgeometry pgGeometry = (PGgeometry) rs.getObject(columnName);
        return getResult(pgGeometry);
    }

    @SneakyThrows
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) {
        PGgeometry pgGeometry = (PGgeometry) rs.getObject(columnIndex);
        return getResult(pgGeometry);
    }

    @SneakyThrows
    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) {
        PGgeometry string = (PGgeometry) cs.getObject(columnIndex);
        return getResult(string);
    }

    private T getResult(PGgeometry pGgeometry) {
        if (pGgeometry == null) {
            return null;
        }
        String pgWkt = pGgeometry.toString();
        String target = String.format("SRID=%s;", SRID_IN_DB);
        String wkt = pgWkt.replace(target, "");
        try {
            return (T) READER_POOL.get().read(wkt);
        } catch (Exception e) {
            throw new RuntimeException("解析wkt失败：" + wkt, e);
        }
    }
}