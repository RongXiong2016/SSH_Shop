import java.util.Date;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.shop.model.Category;
import com.ssh.shop.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class Test {
	@Resource
	private Date date;
	@Resource
	private CategoryService categoryService;
	@org.junit.Test
	public void SpringIoc(){
		System.out.println(date);
	}
 
	/*	@org.junit.Test
	public void hibernateAndSpring(){
		categoryService.update(new Category(5,"女士",false));
	}*/
	
	@org.junit.Test
	public void testquerjtiyjoin(){
		for (Category c : categoryService.queryJoinAccount("",1,2)) {
			System.out.println(c+","+c.getAccount());
		}
	}
}
