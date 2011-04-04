import java.util.UUID;

/**
 * 随便写点测试
 * @author lection
 *
 */
public class Test {
	public static void main(String[] args){
		for(int i=5000;i<5100;i++){
		System.out.println("INSERT INTO `t_book` VALUES ('"+i
		+"', '"+UUID.randomUUID().toString().substring(1, 7)+"', 'test"+(i%10)+"', 'test', '42', 'test', 'test', 'y(HowardMarshall)Part2', 'yue(HowardMarshall)Part2', 'e1dcd8d4-0b62-4c68-8e5d-9bee2041da36.pdf', '0', '0', '2', '2011-04-25', 'default.jpg', 'e1dcd8d4-0b62-4c68-8e5d-9bee2041da36', '14', '3', '', '2011-04-04 01:07:49', 'admin');");
		}
	}
}
