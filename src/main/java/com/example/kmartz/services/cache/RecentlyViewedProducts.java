package com.example.kmartz.services.cache;

import com.example.kmartz.models.Product;
import java.util.HashMap;
import java.util.Map;

public class RecentlyViewedProducts {

    public final int maxSize;
    public Node head;
    public Node tail;

    public class Node {
        public Product product;
        public Node prev;
        public Node next;

        Node(Product product) {
            this.product = product;
        }
    }

    public RecentlyViewedProducts(int maxSize) {
        this.maxSize = maxSize;
    }

    public void addProduct(Product product) {
        // Check if already exists, remove it first
        Node current = head;
        while (current != null) {
            if (current.product.getProductId().equals(product.getProductId())) {
                removeNode(current);
                break;
            }
            current = current.next;
        }

        // Add at head
        Node newNode = new Node(product);
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
        if (tail == null) tail = head;

        // Remove tail if size exceeds maxSize
        if (size() > maxSize) {
            removeNode(tail);
        }
    }

    public void removeNode(Node node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
    }

    public Node getHead() {
        return head;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
