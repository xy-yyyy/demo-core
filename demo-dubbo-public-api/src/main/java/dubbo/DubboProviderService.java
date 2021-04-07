package dubbo;

import java.util.List;
import java.util.Map;

/**
 * Dubbo对外暴露的接口（生产者）
 * @Author: sunYF
 * @Description:
 * @Date: Create in 15:16 2020/8/28
 */
public interface DubboProviderService {

    String hello(String name);

    List<Map<String, Object>> testMapList(Map<String, Object> map);
}
