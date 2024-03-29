package com.ivyzh.datastructures.queue;

import java.util.Scanner;


/**
 * (rear + 1) % maxSize == front  表示满
 * rear == front 表示空
 * 计算队列有多个元素=(rear + maxSize - front) % maxSize
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean looper = true;
        while (looper) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据");
            System.out.println("g(get)：取出数据");
            System.out.println("h(head)：查看数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    looper = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int ele = scanner.nextInt();
                    queue.addQueue(ele);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }

                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.println("查看的数据是：" + result);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        System.out.println("程序退出~~");

    }


    static class CircleArrayQueue {
        int[] arr;
        int maxSize;
        int front;
        int rear;

        public CircleArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            front = 0;//指向队列第一个元素
            rear = 0;//指向队列最后一个元素的后一个位置
        }

        // 队列是否已经满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        // 队列是否已经空
        public boolean isEmpty() {
            return front == rear;
        }

        // 添加元素
        public void addQueue(int ele) {
            if (isFull()) {
                System.out.println("队列已满，不能添加元素");
                return;
            }
            arr[rear] = ele;
            rear = (rear + 1) % maxSize;
        }

        // 打印队列
        public void showQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                return;
            }
            // 打印有效值
            System.out.println("打印有效值：");
            for (int i = front; i < front + size(); i++) {
                System.out.print(arr[(i % maxSize)] + "->");
            }
            System.out.println();
            System.out.println("打印完整队列：");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + "->");
            }
            System.out.println();
        }

        // 有效数据
        private int size() {
            return (rear + maxSize - front) % maxSize;
        }

        // 查看队列头元素
        public int headQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("这一个空队列");
            }
            // 打印有效值
            return arr[front];
        }

        // 出队列操作
        public int getQueue() {
            if (isEmpty()) {
                System.out.println("空队列");
                throw new RuntimeException("这一个空队列");
            }
            // 打印有效值
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }
    }
}
