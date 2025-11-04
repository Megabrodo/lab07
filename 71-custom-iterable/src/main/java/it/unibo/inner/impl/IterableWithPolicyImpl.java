package it.unibo.inner.impl;
import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private final T[] elems;
    private Predicate<T> pr;

    public IterableWithPolicyImpl(T[] array, Predicate<T> filter){
        this.elems = array;
        this.pr = filter;
    }

    public IterableWithPolicyImpl(T[] elems){
        this(
            elems,
            new Predicate<T>() {
                @Override
                public boolean test(T elem){
                    return true;
                }
            });
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.pr = filter;
    }

    public java.util.Iterator<T> iterator() {
       return this.new Iterator();
    }

    private class Iterator implements java.util.Iterator<T>{
        private int current=0;

        public boolean hasNext(){
            for(; current<elems.length; current++){
                if (pr.test(elems[current])){
                    return true;
                }
            }
            return false;
        }

        public T next(){
            if (hasNext()){
                return elems[current++];
            }
            else{
                return null;
            }
        }
    }
}