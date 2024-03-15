package org.wh.park.test_mp.config;

import com.aliyun.tea.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class IdTableNameHandler implements TableNameHandler {

    /**
     * 哪些表可以使用这个动态表名规则
     * 表名-> 分几张表
     */
    private static Map<String, Integer> configTableInfoMap = new HashMap<>();

    static {
        configTableInfoMap.put("p_user_record", 2);
    }

    //避免多线程数据冲突，使用ThreadLocal
    private static final ThreadLocal<Long> idData = new ThreadLocal<>();


    /**
     * 在具体方法分表之前指定要依据分表的id
     *
     * @param id
     */
    public static void initCurrentId(Long id) {
        idData.set(id);
    }


    public static Long getCurrentId() {
        return idData.get();
    }


    /**
     * 在使用结束之后，要进行remove防止内存泄露
     */
    public static void removeCurrentId() {
        idData.remove();
    }


    @Override
    public String dynamicTableName(String sql, String tableName) {
        if (StringUtils.isEmpty(tableName) ||
                !configTableInfoMap.containsKey(tableName)) {
            return tableName;
        }

        // 分表个数
        Integer tableSize = configTableInfoMap.get(tableName);

        //当前要分表的id
        Long currentId = getCurrentId();
        //当前id的数据应该保存在第几张标
        int tableIndex = (int) (currentId % tableSize);
        System.out.println(tableName + "_" + tableIndex);


        //删除当前id
        removeCurrentId();
        return tableName + "_" + tableIndex;
    }
}
