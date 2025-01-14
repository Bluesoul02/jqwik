package net.jqwik.api.lifecycle;

import java.lang.reflect.*;

import org.apiguardian.api.*;

import net.jqwik.api.*;

import static org.apiguardian.api.API.Status.*;

/**
 * The context information of a single try of a property.
 */
@API(status = MAINTAINED, since = "1.4.0")
public interface TryLifecycleContext extends LifecycleContext {

	/**
	 * The method that defines the current property or example.
	 *
	 * @return a Method instance
	 */
	Method targetMethod();

	/**
	 * The container class in which the current property method is running.
	 *
	 * <p>
	 * Most of the time that's also the defining class. It differs when
	 * running properties that are defined in a super class or an implemented interface.
	 * </p>
	 *
	 * @return a Class instance
	 */
	Class<?> containerClass();

	/**
	 * The current instance of the property's container class.
	 * There is exactly one instance per property method.
	 *
	 * <p>
	 * Mind that all tries of a property <em>share the same test instance</em>.
	 * </p>
	 *
	 * @return an instance of the container class in which the current property method is running
	 */
	Object testInstance();

}
