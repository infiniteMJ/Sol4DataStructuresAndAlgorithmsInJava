import java.util.*;


class HighArray {
    private long[] a;
    private int nElem;

    public HighArray(int max) {
        a = new long[max];
        nElem = 0;
    }

    public boolean find(long searchKey) {
        for (int j = 0; j < nElem; j++) {
            if (a[j] == searchKey) {
                return true;
            }
        }
        return false;

    }
    public void insert(long value) {
        a[nElem++] = value;
    }

    public boolean delete(long value) {
        int j;

        for (j = 0; j < nElem; j++) {
            if (a[j] == value) {
                for (int k = j; k < nElem; k++) {
                    a[k] = a[k + 1];
                }
                nElem--;
                return true;
            }
        }

        return !(j == nElem);
    }

    public void display() {
        for (int j = 0; j < nElem; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    // Problem 2.1
    public long getMax() {
        if (nElem == 0) {
            return -1;
        } 

        long maxNum = Long.MIN_VALUE;
        for (int j = 0; j < nElem; j++) {
            if (a[j] >= maxNum) {
                maxNum = a[j];
            }
        }
        return maxNum;
    }

    // Problem 2.2
    public long removeMax() {
        if (nElem == 0) {
            return -1;
        }

        long maxNum = this.getMax();
        for (int j = 0; j < nElem; j++) {
            if (a[j] == maxNum) {
                for (int k = j; k < nElem; k++) {
                    a[k] = a[k + 1];
                }
                nElem--;
            }
        }
        return maxNum;
    }

    // Problem 2.6
    public void merge(OrdArray orderArr) {
        int n1 = nElem - 1;
        int n2 = orderArr.size() - 1;
        nElem += orderArr.size();
        int n = nElem - 1;

        while (n1 >= 0 || n2 >= 0) {
            if (n1 == -1) {
                a[n--] = orderArr.a[n2--];
            } else if (n2 == -1) {
                a[n--] = a[n1--];
            } else if (a[n1] >= orderArr.a[n2]) {
                a[n--] = a[n1--];
            } else if (a[n1] < orderArr.a[n2]) {
                a[n--] = orderArr.a[n2--];
            }
        }
    }
    
    // Problem 2.6 static method
    public void noDup() {
        Set<Long> hashSet = new HashSet<Long>();

        int newLen = 0;
        for (int j = 0; j < nElem; j++) {
            if (!hashSet.contains(a[j])) {
                a[newLen++] = a[j];
                hashSet.add(a[j]);
            }
        }
        nElem = newLen;

    }

}


class OrdArray {
    private long[] a;
    private int nElem;

    public OrdArray(int max) {
        a = new long[max];
        nElem = 0;
    }

    public int size() {
        return nElem;
    }

    public int find(long searchKey) {
        int left = 0;
        int right = nElem - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == searchKey) {
                return mid;
            } else if (a[mid] > searchKey) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Problem 2.4
    public void insert(long value) {

        int left = 0;
        int right = nElem++ - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] > value) {
                right = mid - 1;
            } else if (a[mid] < value){
                left = mid + 1;
            } else {
                right = mid;
                break;

            }
        }
        int insertLoc = right + 1;

        for (int j = nElem - 1; j > insertLoc; j--) {
            a[j] = a[j - 1];
        }
        a[insertLoc] = value;
    }

    public boolean delete(long value) {
        int j = this.find(value);
        if (j == -1) {
            return false;
        } 
        for (int k = j; k < nElem; k++) {
            a[k] = a[k + 1];
        }
        nElem--;
        return true;
    }

    public void display() {
        for (int j = 0; j < nElem; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }
    
    // Problem 2.5
    public static OrdArray merge(OrdArray orderArr1, OrdArray orderArr2) {
        int n1 = orderArr1.size();
        int n2 = orderArr2.size();
        OrdArray res = new OrdArray(n1 + n2);
        int i = 0;
        int j = 0;
        
        while (i < n1 || j < n2) {
            if (i == n1) {
                res.insert(orderArr2.a[j++]);
            } else if (j == n2) {
                res.insert(orderArr1.a[i++]);
            }else if (orderArr1.a[i] >= orderArr2.a[j]) {
                res.insert(orderArr2.a[j++]);
            } else if (orderArr1.a[i] < orderArr2.a[j]) {
                res.insert(orderArr1.a[i++]);
            }
        }

        return res;
    }
}


public class ImplementArray {
    public static void main(String[] args) {
        int maxSize = 100;
        
        HighArray arr = new HighArray(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(100);
        arr.insert(33);
        arr.insert(55);
        arr.insert(33);
        arr.insert(66);
        arr.insert(77);
        arr.insert(88);
        arr.insert(17);
        arr.insert(18);
        arr.insert(19);
        arr.insert(17);
        arr.insert(17);
        arr.insert(29);
        arr.display();
        arr.noDup();
        arr.display();
        
        // Problem 2.3
        HighArray newArr = new HighArray(maxSize);
        while (arr.find(arr.getMax())) {
            newArr.insert(arr.removeMax());
        }
        newArr.display();
        
    }
}

