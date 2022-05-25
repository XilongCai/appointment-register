import java.util.ArrayList;
import java.util.List;

class CountIntervals {

    List<int[]> list;
    int count;

    public CountIntervals() {
        this.list = new ArrayList<>();
        this.count = 0;
    }

    public void add(int left, int right) {
        int leftAdd, rightAdd;
        if (list.size() == 0) {
            list.add(new int[] {left, right});
            count += (right - left + 1);
            return;
        }
        int leftDelete = binarySearch(left, list);
        int rightDelete = binarySearch(right, list);
        if (leftDelete == -1) {
            leftDelete = 0;
            leftAdd = left;
        }
        if (leftDelete == list.size()) {
            list.add(new int[] {left, right});
            count += (right - left + 1);
            return;
        }
        if (rightDelete == -1) {
            list.add(0, new int[] {left, right});
            count += (right - left + 1);
            return;
        }
        if (rightDelete == list.size()) {
            rightDelete = list.size() - 1;
            rightAdd = Math.max(right, list.get(list.size() - 1)[1]);
        }

        if (left > list.get(leftDelete)[1]) {
            leftDelete++;
            leftAdd = left;
        } else {
            leftAdd = list.get(leftDelete)[0];
        }

        if (right > list.get(rightDelete)[1]) {

            rightAdd = right;
        } else {
            rightAdd = list.get(rightDelete)[1];
        }

        for (int i = 0; i <= rightDelete - leftDelete; ++i) {
            count -= (list.get(leftDelete)[1] - list.get(leftDelete)[0] + 1);
            list.remove(leftDelete);
        }
        list.add(leftDelete, new int[] {left, right});
        count += (right - left + 1);

    }

    public int binarySearch(int num, List<int[]> list) {
        int low = 0, high = list.size() - 1;
        int delete = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid)[0] <= num) {
                delete = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return delete;
    }

    public int count() {
        return count;
    }
}

public class test {
    public static void main(String[] args) {
        CountIntervals obj = new CountIntervals();
        obj.add(39, 44);
        obj.add(13, 49);
        System.out.println(obj.count());
    }

}
