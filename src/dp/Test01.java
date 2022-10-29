package dp;

public class Test01 {

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        boolean canJump = test01.canJump(new int[]{2, 3, 1, 1, 4});
        System.out.println(canJump);
    }


    //==========================================跳跃游戏==================================

    public boolean canJump(int[] nums) {
        int maxStep = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxStep >= nums.length - 1) {
                return true;
            }
            if(i > maxStep) return false;
            maxStep = Math.max(maxStep, i + nums[i]);
        }
        return false;
    }
}
