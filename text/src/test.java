public class test {
    public static void main(String[] args) {
        User user  =new User();
        User user1 =new User();
        user.setName("zhangsan");
        user.setAge(22);
        user1.setName("zhangsan");
        user1.setAge(22);
        boolean equals = user.equals(user1);
        System.out.println(equals);
    }
}
