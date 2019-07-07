package com.zys.day14.repository;

import com.zys.day14.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

//继承JpaRepository,会自动生成内置方法。可以根据方法名自动产生SQL
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username,String email);

    /**
     * 自定义SQL
     * @param pageable
     * @return
     */
    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);

    Page<User> findByNickName(String nickName, Pageable pageable);

    Slice<User> findByNickNameAndEmail(String nickName, String email, Pageable pageable);

    /**
     * Page 和 Slice 的区别如下。
     *
     * Page 接口继承自 Slice 接口，而 Slice 继承自 Iterable 接口。
     * Page 接口扩展了 Slice 接口，添加了获取总页数和元素总数量的方法，因此，返回 Page 接口时，必须执行两条 SQL，一条复杂查询分页数据，另一条负责统计数据数量。
     * 返回 Slice 结果时，查询的 SQL 只会有查询分页数据这一条，不统计数据数量。
     * 用途不一样：Slice 不需要知道总页数、总数据量，只需要知道是否有下一页、上一页，是否是首页、尾页等，比如前端滑动加载一页可用；而 Page 知道总页数、总数据量，可以用于展示具体的页数信息，比如后台分页查询。
     *
     */

/*    @Query("select u from User u where u.nickName = :nickName")
    Page<User> findByNickName(@Param("nickName") String nickName, Pageable pageable);*/

    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String  userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);
}
