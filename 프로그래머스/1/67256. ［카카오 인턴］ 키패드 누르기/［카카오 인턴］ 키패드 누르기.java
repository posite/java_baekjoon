import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        int left = 10, right = 12;
        boolean leftCenter = false, rightCenter = false;
        StringBuilder sb = new StringBuilder();
        for(int number: numbers) {
            System.out.print("number: " + number + " ");
            System.out.println("left " + left + " right: " + right);
            if(number == 0) {
                int leftDistance = Math.abs(3 - left/3) + 1;
                int rightDistance = Math.abs(3 - ((right+1)/3 -1))+ 1;
                if(leftCenter) leftDistance--;
                if(rightCenter) rightDistance--;
                if(leftDistance > rightDistance) {
                    right = 11;
                    rightCenter = true;
                    sb.append("R");
                } else if(rightDistance > leftDistance) {
                    sb.append("L");
                    left = 11;
                    leftCenter = true;
                } else {
                    if(hand.equals("left")) {
                        sb.append("L");
                        left = 11;
                        leftCenter = true;
                    } else {
                        right = 11;
                        rightCenter = true;
                        sb.append("R");
                    }
                }
                continue;
            }
            if(number%3 == 1) {
                sb.append("L");
                left = number;
                //System.out.println(left);
                leftCenter = false;
            } else if(number%3 == 0) {
                sb.append("R");
                right = number;
                //System.out.println(right);
                rightCenter = false;
            } else {
                int leftDistance = Math.abs(number/3 - left/3) + 1;
                int rightDistance = Math.abs(number/3 - ((right+1)/3 -1) ) + 1;
                if(leftCenter) leftDistance--;
                if(rightCenter) rightDistance--;
                System.out.println("leftCenter: " + leftCenter + " rightCenter: " + rightCenter);
                System.out.println(leftDistance + " " + rightDistance);
                if(leftDistance > rightDistance) {
                    right = number;
                    rightCenter = true;
                    //System.out.println(right);
                    sb.append("R");
                } else if(rightDistance > leftDistance) {
                    sb.append("L");
                    left = number;
                    //System.out.println(left);
                    leftCenter = true;
                } else {
                    System.out.println(hand);
                    if(hand.equals("left")) {
                        sb.append("L");
                        left = number;
                        //System.out.println(left);
                        leftCenter = true;
                    } else {
                        right = number;
                        rightCenter = true;
                        //System.out.println(right);
                        sb.append("R");
                    }
                }
            }
        }
    
        return sb.toString();
    }
}