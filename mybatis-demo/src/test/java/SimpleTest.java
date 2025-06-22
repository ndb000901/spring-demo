import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.demo.mybatis.mapper.UserMapper;
import com.hello.demo.mybatis.pojo.User;
import com.hello.demo.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SimpleTest {

    @Test
    public void getUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        System.out.println(mapper.getUserById(new BigInteger("1")));
        System.out.println(mapper.getUserById(new BigInteger("1")));

    }

    @Test
    public void testLocalCache() {
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);

        System.out.println("mapper1->1: " + mapper1.getUserById(new BigInteger("1")));

        System.out.println("mapper2->1: " + mapper2.getUserById(new BigInteger("1")));
        System.out.println("mapper1->2: " + mapper1.getUserById(new BigInteger("1")));

    }

    //<setting name="cacheEnabled" value="true"/>
    @Test
    public void testGlobalCache() {
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);


        System.out.println("mapper1->1: " + mapper1.getUserById(new BigInteger("1")));
        System.out.println("mapper1->2: " + mapper1.getUserById(new BigInteger("1")));

        sqlSession1.commit();


        System.out.println("mapper2->1: " + mapper2.getUserById(new BigInteger("1")));


    }

    @Test
    public void testGetUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.getUser(new BigInteger("1"), "hello@gmail.com"));
    }

    @Test
    public void testPage() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(1, 1); // 第1页，每页2条
        List<User> users = mapper.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users); // 封装分页信息
        System.out.println(pageInfo);
    }

}
