/*
 *Project: manager
 *File: com.diyun.manager.utils.ObjectUtils.java <2018年12月07日}>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/
package ${basepackage}.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

/**
 * @author lizhou
 * @version 1.0
 * @Date 2018年12月07日 17时03分
 */
public class ObjectUtils {
    private static final Logger log = LogManager.getLogger(ObjectUtils.class);
    /**
     * 判断一个对象中，所有的(数值、String、List、Map等类型的)业务属性都没有值，
     * 若这些属性 假如 全都没有合法值，则返回true;否则返回false
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static  boolean allFieldWithoutValue(Object obj) {
        if(null == obj){
            return true;
        }
        try{
            for(Field f : obj.getClass().getDeclaredFields()){
                f.setAccessible(true);
                if("serialVersionUID".equalsIgnoreCase(f.getName())){
                    continue; //序列化的属性不处理
                }
                if( !StringUtils.isEmpty(f.get(obj)+"") ){
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * map 转成Java bean
     * @param map
     * @param user
     * @throws Exception
     */
    @SuppressWarnings("all") //消除警告
    public static void populate(Map<String, Object> map, Object objInstance) throws Exception {

        //拿到 对应的class对象
        Class clazz = objInstance.getClass();

        //通过反射拿到所有的属性
        //拿到所有的属性值
        Field[] fields = clazz.getDeclaredFields();

        //遍历属性
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];

            //获取属性的名称
            String key = f.getName();

            //跳过属性的权限检查
            f.setAccessible(true);

            //序列化的属性不处理
            if("serialVersionUID".equalsIgnoreCase(f.getName())){
                continue;
            }

            //都是字符串 操作  进行判断
            //如果属性的类型是int 判断int.class   如果是Ingeter 判断为 Integer.class
            if (f.getType() == int.class){
                f.set(objInstance,Integer.valueOf(map.get(key)+""));
            }else if (f.getType() == double.class){
                f.set(objInstance,Double.valueOf(map.get(key)+""));
            }else if (f.getType() == BigDecimal.class){
                if(null == map.get(key) ){
                    f.set(objInstance,new BigDecimal(new Double(0)));
                }else{
//                    System.out.println("key=" + key + ",value=" + map.get(key) +",class=" + map.get(key).getClass());
                    f.set(objInstance,new BigDecimal(map.get(key)+""));
                }
            }else if (f.getType() == Timestamp.class){
                if(null == map.get(key)){
                    f.set(objInstance,null);
                }else{
                    f.set(objInstance,new Timestamp(Long.valueOf(map.get(key)+"")));
                }

            }else{
                f.set(objInstance,map.get(key));
            }

        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setUsername("lizhou");
        System.out.println(allFieldWithoutValue(user));
    }



}

class User{
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
