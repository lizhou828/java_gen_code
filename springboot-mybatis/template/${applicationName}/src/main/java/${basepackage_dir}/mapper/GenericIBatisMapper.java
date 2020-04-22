package ${basepackage}.mapper;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 业务底层 公共Mapper接口
 * @param <T>  实体类
 * @param <PK> 主键类型
 */
public interface GenericIBatisMapper<T, PK extends Serializable> {
    int save(T var1);

    int deleteByPK(PK var1);

    void deleteByPKeys(@Param("primaryKeys") List<PK> var1);

    int deleteByProperty(T var1);

    int update(T var1);

    T getByPK(PK var1);

    List<T> list();

    List<T> listByProperty(T var1);

    int findByCount(T var1);

    public Page<T> findByPage(Page<T> page, T t);

    int batchInsert(List<T> list);
}
