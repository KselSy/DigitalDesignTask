package task;

public class Main {

    public static void main(String[] args) {
        String test = "2[A]2[B]2[C]2[D]";
        System.out.println(correctString(test, maxDeep(test)));
    }
    public static boolean isValid(String input) {
        int valid = 0;
        if (input.isEmpty() || input.charAt(0) == '[')
            return false;
        try {
            Integer.parseInt(input);
            return false;
        }
        catch (NumberFormatException e){}
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 48 && input.charAt(i) <= 57)
                if(input.charAt(i+1) < 48 || input.charAt(i+1) > 57 && input.charAt(i+1) != '[')
                    return false;
            if (input.charAt(i) == '['){
                if (input.charAt(i-1) > 57 || input.charAt(i-1) < 48)
                    return false;
                valid++;
            }
            char currentChar = input.charAt(i);
            if ((currentChar < '0' || currentChar > '9') &&
                    currentChar != '[' && currentChar != ']' &&
                    (currentChar < 'a' || currentChar > 'z') &&
                    (currentChar < 'A' || currentChar > 'Z'))
                return false;
            if (input.charAt(i) == ']')
                valid--;
            if (valid < 0)
                return false;
        }
        return valid == 0;
    }
    public static int maxDeep(String input){
        int currentDeep = 0;
        int res = 0;
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '['){
                currentDeep++;
                if (currentDeep > res)
                    res = currentDeep;
            }
            if (input.charAt(i) == ']')
                currentDeep--;
        }
        return res;
    }
    public static String correctString(String input, int maxDeep){
        if (!isValid(input))
            return "invalid string";
        int currentDeep = 0;
        int num, end, val;
        String insert = "";
        StringBuilder res = new StringBuilder(input);

        for (int i = 0; i < res.length(); i++){
            if (res.charAt(i) == '['){
                currentDeep++;
                if (currentDeep == maxDeep){
                    end = i;
                    num = i-1;
                    while (res.charAt(num) >= 48 && res.charAt(num) <= 57 && num > 0){
                        num--;
                    }
                    if(num!=0)
                        num++;
                    val = Integer.parseInt(res.substring(num,i));
                    while (res.charAt(end) != ']')
                        end ++;
                    insert = multipleSubstring(val, res.substring(i+1, end));
                    res.delete(num,end+1);
                    res.insert(num,insert);
                    currentDeep--;
                    continue;
                }
            }
            if (res.charAt(i) == ']')
                currentDeep--;
        }
        if (maxDeep > 0){
            maxDeep--;
            return correctString(res.toString(), maxDeep);
        }
        return res.toString();
    }
    public static String multipleSubstring(int val, String str){
        String res = "";
        for (int i = 0; i < val; i++){
            res += str;
        }
        return res;
    }
}
