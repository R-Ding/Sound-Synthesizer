package edu.caltech.cs2.project03;

import edu.caltech.cs2.datastructures.CircularArrayFixedSizeQueue;
import edu.caltech.cs2.interfaces.IFixedSizeQueue;
import java.util.Random;


public class CircularArrayFixedSizeQueueGuitarString {
    private static final double samplingRate = 44100;
    private static final double energyDecayFactor = 0.996;
    private static final Random RANDOM = new Random();
    private IFixedSizeQueue queue;

    public CircularArrayFixedSizeQueueGuitarString(double frequency) {
        int n = (int)Math.ceil(this.samplingRate / frequency);
        this.queue = new CircularArrayFixedSizeQueue(n);
        for (int i = 0; i < n; i++) {
            this.queue.enqueue(0.0);
        }
    }

    public int length() {
        return this.queue.capacity();
    }

    public void pluck() {
        for (int i = 0; i < this.length(); i++) {
            double rand = -0.5 + (RANDOM.nextDouble());
            this.queue.dequeue();
            this.queue.enqueue(rand);
        }
    }

    public void tic() {
        double first = (double)this.queue.dequeue();
        double second = (double)this.queue.peek();
        double average = (first + second) * 0.5 * this.energyDecayFactor;
        this.queue.enqueue(average);

    }

    public double sample() {
        return (double)this.queue.peek();
    }
}
