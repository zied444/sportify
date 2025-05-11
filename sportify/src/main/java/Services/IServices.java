package Services;

import java.util.List;

public interface IServices<T> {
    void create (T t) throws Exception;
    void update (T t)throws Exception;
    void delete (T t)throws Exception;
    List<T> display()throws Exception;
}
