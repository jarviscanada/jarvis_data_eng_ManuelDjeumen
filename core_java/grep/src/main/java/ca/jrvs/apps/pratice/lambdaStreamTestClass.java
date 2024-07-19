package ca.jrvs.apps.pratice;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class lambdaStreamTestClass implements lambdaStreamExc{

    public lambdaStreamTestClass(){

    }

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Arrays.stream(strings.clone());
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        List<String> list = new ArrayList<>();
        for (String s: strings){
            list.add(s.toUpperCase());
        }
        return list.stream();
//        return createStrStream(strings);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(string -> !string.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr.clone());
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        List<Integer> list = toList(intStream);
        double[] list2 = new double[list.size()];
        for (int i =0; i < list.size(); i++){
            list2[i] = Math.sqrt(list.get(i));
        }
        return DoubleStream.of(list2);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        List<Integer> list = toList(intStream);
        int[] list2 = new int[list.size()];
        for (int i =0; i < list.size(); i++){
            if (list.get(i) % 2 != 0){
                list2[i] = list.get(i);
            }
        }
        return IntStream.of(list2);
    }


    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     *
     * You will learn:
     *   - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
     *   - lambda syntax
     *
     * e.g.
     * LambdaStreamExc lse = new LambdaStreamImp();
     * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
     * printer.accept("Message body");
     *
     * sout:
     * start>Message body<end
     *
     * @param prefix prefix str
     * @param suffix suffix str
     * @return
     */

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return null;
    }


    /**
     * Print each message with a given printer
     * Please use `getLambdaPrinter` method
     *
     * e.g.
     * String[] messages = {"a","b", "c"};
     * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
     *
     * sout:
     * msg:a!
     * msg:b!
     * msg:c!
     *
     * @param messages
     * @param printer
     */

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {

    }


    /**
     * Print all odd number from a intStream.
     * Please use `createIntStream` and `getLambdaPrinter` methods
     *
     * e.g.
     * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
     *
     * sout:
     * odd number:1!
     * odd number:3!
     * odd number:5!
     *
     * @param intStream
     * @param printer
     */
    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {

    }

    /**
     * Square each number from the input.
     * Please write two solutions and compare difference
     *   - using flatMap
     *
     * @param ints
     * @return
     */
    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return null;
    }

}

