package yarden_perets_214816407;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

public abstract class ElementManager<E> implements Serializable, Solutionable, Iterable<E>{

	protected LinkedHashSet<E> elements;
	private static final long serialVersionUID = Repo.REPO_VERSION;
	
	public ElementManager() {
		this.elements = new LinkedHashSet<>();
	}
	
	public ElementManager(LinkedHashSet<E> elements) {
		this.elements = new LinkedHashSet<>(elements);
	}
	
	public ElementManager(ElementManager<E> elementManager) {
		this(elementManager.elements);
	}
	
	public abstract boolean addElement(E element);
	public abstract E getElement(int key);
	
	public E getElement(Object key, Comparator<Object> comparator) {
		for (E curr : elements) {
			if (comparator.compare(curr, key) == 0)
				return curr;
		}
		return null;
	}
	
	public boolean deleteElement(int key) {
		if(elements.isEmpty())
			return false;
		return elements.remove(getElement(key));
	}
	
	public int size() {
		return elements.size();
	} 
	
	public void clear() {
		elements.clear();
	} 
	
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		for(E element : elements) {
			builder.append(element.toString());
		}
		return builder.toString();
	}
	
	@Override
	public abstract String getSolution();

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(obj instanceof ElementManager<?>) {
			ElementManager<?> other = (ElementManager<?>) obj;
			return Objects.equals(elements, other.elements);
		}
		return false;
	}
	
	

}
