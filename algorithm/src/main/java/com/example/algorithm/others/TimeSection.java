package com.example.algorithm.others;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Component
@Data
public class TimeSection {
    private String name;
    public List<List<Integer>> getResult(List<List<Integer>> times) {
        PriorityQueue<List<Integer>> small = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        for (List<Integer> item : times) {
            small.add(item);
        }
        List<List<Integer>> result = new ArrayList<>();

        while (small.size() > 0) {
            List<Integer> first = small.poll();
            List<Integer> second = small.poll();
            if (second == null) {
                result.add(first);
                continue;
            }
            if (first.get(1) < second.get(0)) {
                result.add(first);
                small.add(second);
            } else if (first.get(1) > second.get(1)) {
                small.add(first);
            } else if (first.get(1) > second.get(0) && first.get(1) < second.get(1)) {
                // 融合
                List<Integer> merge = merge(first, second);
                small.add(merge);
            }
        }
        return result;
    }

    public List<Integer> merge(List<Integer> first, List<Integer> second) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(first.get(0));
        integers.add(second.get(1));
        return integers;
    }
}
