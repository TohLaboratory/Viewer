public class QuickSortTest {
    // 配列dのleftからrightまでの間のデータ列をクイックソートする
    static void quick_sort(int[] d, int left, int right) {
        if (left>=right) {
            return;
        }
        int p = d[(left+right)/2];
        int l = left, r = right, tmp;
        while(l<=r) {
            while(d[l] < p) { l++; }
            while(d[r] > p) { r--; }
            if (l<=r) {
                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
                l++; r--;
            }
        }
        quick_sort(d, left, r);  // ピボットより左側をクイックソート
        quick_sort(d, l, right); // ピボットより右側をクイックソート
    }
    // 配列内のデータ列を表示する
    static void print_data(int[] d) {
        for(int i = 0; i < d.length; i++) System.out.print(d[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] data = {5, 10, 3, 7, 8, 1, 9, 2};
        print_data(data);
        quick_sort(data, 0, data.length-1);
        print_data(data);
    }
}
