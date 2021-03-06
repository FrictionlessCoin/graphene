package graphene.util.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 
 * @author djue
 * 
 * @param <T>
 *            The class of object you are serializing, usually a POJO based on a
 *            DB call.
 * @param <Q>
 */
public class KryoDiskCache<T, Q> implements DiskCache<T, Q> {

	private static final long FLUSH_THRESHOLD = 10000;

	@Inject
	private Logger logger;

	long numberOfRecordsCached = 0;

	private Kryo kryo;
	private Input input;
	private Output output;
	private Class<T> clazz;

	public KryoDiskCache() {
		kryo = new Kryo();

	}

	@Override
	public void init(Class<T> clazz) {
		kryo.register(clazz);
		this.clazz = clazz;
	}

	/**
	 * In DiskCache, this callback writes the object t to an output stream,
	 * usually a file.
	 */
	public boolean callBack(T t) {
		return write(t);
	}

	/**
	 * closes any input or output streams that are open.
	 */
	public void closeStreams() {
		if (output != null) {
			output.close();
		}
		if (input != null) {
			input.close();
		}
	}

	public boolean dropExisting(String fileName) {
		boolean deleted = false;
		try {
			logger.debug("Attempting to delete " + fileName);
			File f = new File(fileName);
			deleted = f.delete();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return deleted;
	}

	@Override
	public boolean initializeReader(String fileName) {
		input = null;
		File f = new File(fileName);
		if (f.exists() && f.length() > 0) {
			logger.debug("File found: " + fileName);
			try {
				logger.debug("Creating file: " + fileName);
				input = new Input(new FileInputStream(fileName));
			} catch (FileNotFoundException e) {
				logger.error("Expected to create the file " + fileName
						+ ", but could not.");
				logger.error(e.getMessage());
			}
		} else {
			logger.debug("File not found: '" + fileName + "'");
		}

		return input != null ? true : false;
	}

	@Override
	public boolean initializeWriter(String fileName) {
		output = null;
		try {
			output = new Output(new FileOutputStream(fileName));
			numberOfRecordsCached = 0;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return output != null ? true : false;
	}

	@Override
	public T read() {
		// logger.debug("Reading with Kryo");
		T t = null;
		try {
			if (input != null && !input.eof()) {
				t = kryo.readObject(input, clazz);
				// logger.debug("Read " + t);
			} else {
				if (input == null) {
					logger.error("The input stream was null for the kryo cache");
				}
				logger.debug("EOF Reached");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return t;
	}

	public boolean write(T s) {
		kryo.writeObject(output, s);
		numberOfRecordsCached++;
		if (numberOfRecordsCached % FLUSH_THRESHOLD == 0) {
			output.flush();
		}
		return true;
	}

	@Override
	public long getNumberOfRecordsCached() {
		return numberOfRecordsCached;
	}

	@Override
	public boolean callBack(T t, Q q) {
		return write(t);
	}

}
