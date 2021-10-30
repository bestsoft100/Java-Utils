package b100.utils;

import b100.utils.math.Vec3i;

@SuppressWarnings("unchecked")
public class Array3D<E>{
	
	private final Object[] data;
	
	public final int width;
	public final int height;
	public final int depth;
	
	public Array3D(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.data = new Object[size()];
	}
	
	public int size() {
		return width * height * depth;
	}
	
	public Object[] getData() {
		return data;
	}
	
	public void set(int x, int y, int z, E value) {
		testInBounds(x, y, z);
		data[getIndex(x, y, z)] = value;
	}
	
	public void set(Vec3i pos, E value) {
		set(pos.x, pos.y, pos.z, value);
	}
	
	public int getIndex(int x, int y, int z) {
		return z * width * height + y * width + x;
	}
	
	public Vec3i getPosition(int index) {
		int z = index / (width * height);
		int y = (index / (width)) % height;
		int x = index % width;
		return new Vec3i().set(x, y, z);
	}
	
	public E get(int x, int y, int z) {
		testInBounds(x, y, z);
		return (E) data[getIndex(x, y, z)];
	}
	
	public E get(Vec3i pos) {
		return get(pos.x, pos.y, pos.z);
	}
	
	private void testInBounds(int x, int y, int z) {
		if(!isInBounds(x, y, z)) throw new RuntimeException("Out of bounds: [x: "+x+" y: "+y+" z: "+z+"]");
	}
	
	public boolean isInBounds(int x, int y, int z) {
		return x >= 0 && x < width && y >= 0 && y < height && z >= 0 && z < depth;
	}
	
	public boolean isInBounds(Vec3i pos) {
		return isInBounds(pos.x, pos.y, pos.z);
	}
	
	public void fill(E value) {
		for(int i=0; i < data.length; i++) {
			data[i] = value;
		}
	}

	public void fill(int x, int y, int z, int w, int h, int d, E val) {
		for(int i=0; i < w; i++) {
			for(int j=0; j < h; j++) {
				for(int k=0; k < d; k++) {
					set(x + i, y + j, z + k, val);
				}
			}
		}
	}
	
}
