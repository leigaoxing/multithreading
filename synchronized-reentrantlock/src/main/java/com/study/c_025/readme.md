总结：
1.对于map/set的选择
没有并发
HashMap
TreeMap
LinkedHashMap

并发少
HashTable
Collections.synchronizedXXX

高并发
ConcurrentHashMap 无序map
ConcurrentSkipListMap 有序map

2. 队列
没有并发
ArrayList
LinkedList

并发少
Collections.synchronizedXXX
CopyOnWriteList：读多写少的情况下应用

高并发
Queue
    ConcurrentLinkedQueue
    BlockingQueue
        LinkedBQ
        ArrayBQ
        TransferQueue
        SynchronousQueue
    DelayQueue执行定时任务


