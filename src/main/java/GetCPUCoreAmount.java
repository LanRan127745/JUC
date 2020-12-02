public class GetCPUCoreAmount {
    public static void main(String[] args) {
        // 获取CPU的核数
        // CPU密集型、IO密集型
        System.out.println("CPU核心数：" + Runtime.getRuntime().availableProcessors());
    }
}
