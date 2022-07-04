package festival.persistance;

public interface IRepository<ID, T> {
    int save(T entity);
    int delete(ID id);
    int update(ID id,T entity);
    T findOne(ID id);
    T[] findAll();
}