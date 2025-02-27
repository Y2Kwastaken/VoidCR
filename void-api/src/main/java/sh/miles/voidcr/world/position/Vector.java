package sh.miles.voidcr.world.position;

import sh.miles.voidcr.server.VoidCR;
import sh.miles.voidcr.util.serialize.ByteSerializable;

/**
 * A Vector implementation for the VoidCR api
 * <p>
 * While this implementation supports double values internally only float values are allowed. Using these methods to go
 * above float values may cause undefined behavior
 *
 * @since 0.3.27
 */
public interface Vector extends DecimalPosition<Vector>, ByteSerializable {

    /**
     * Negates all vector values
     * <p>
     * A negation is equivalent to providing -1 to {@link #multiply(float, float, float)} for all values
     *
     * @return this vector negated
     * @since 0.3.27
     */
    Vector negate();

    /**
     * Adds these two vectors together directly each component this from this vector is added to the same component from
     * the other vector resulting in a new vector.
     * <p>
     * {@literal <a1, a2, a3> - <b1, b2, b3> = <a1+b1, a2+b2, a3+b3> }
     *
     * @param vector the vector to add with
     * @return the new vector
     * @since 0.3.27
     */
    Vector add(Vector vector);

    /**
     * Subtracts these two vectors together directly each component this from this vector is subtracted to the same
     * component from the other vector resulting in a new vector.
     * <p>
     * {@literal <a1, a2, a3> - <b1, b2, b3> = <a1-b1, a2-b2, a3-b3> }
     *
     * @param vector the vector to add with
     * @return the new vector
     * @since 0.3.27
     */
    Vector subtract(Vector vector);

    /**
     * Multiplies these two vectors together directly each component this from this vector is multiplied to the same
     * component from the other vector resulting in a new vector.
     * <p>
     * {@literal <a1, a2, a3> * <b1, b2, b3> = <a1b1, a2b2, a3b3> }
     *
     * @param vector the vector to add with
     * @return the new vector
     * @since 0.3.27
     */
    Vector multiply(Vector vector);

    /**
     * Multiplies this vector by a scalar
     *
     * @param scalar the scalar
     * @return the new vector
     * @since 0.3.27
     */
    Vector multiply(float scalar);

    /**
     * rotate x on some angle
     *
     * @return the new vector
     * @since 0.3.27
     */
    Vector rotateX(float angle);

    /**
     * rotate y on some angle
     *
     * @param angle the new vector
     * @return the new vector
     * @since 0.3.27
     */
    Vector rotateY(float angle);

    /**
     * rotate z on some angle
     *
     * @param angle the angle
     * @return the new vector
     * @since 0.3.27
     */
    Vector rotateZ(float angle);

    /**
     * Gets the midpoint between two vectors
     *
     * @param vector the middle vector
     * @return the new vector
     * @since 0.3.27
     */
    Vector midpoint(Vector vector);

    /**
     * Normalizes the vector
     *
     * @return the normalized vector
     * @since 0.3.27
     */
    Vector normalize();

    /**
     * Gets the angle in radians between the two vectors
     *
     * @param vector the vector to find the angle between
     * @return the angle, in radians
     * @since 0.3.27
     */
    float angle(Vector vector);

    /**
     * Defines the dot product between this vector and the provided vector
     *
     * @param vector the vector to use
     * @return the dot product
     * @since 0.3.27
     */
    float dot(Vector vector);

    /**
     * Defines the cross product for this vector and the given vector
     *
     * @param vector the vector to cross by
     * @return the cross product
     * @since 0.3.27
     */
    Vector cross(Vector vector);

    /**
     * Gets the magnitude of this vector
     *
     * @return the magnitude double value
     * @since 0.3.27
     */
    float magnitude();

    /**
     * Creates a vector from x, y, and z float values
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @return the resulting created vector
     * @since 0.3.27
     */
    static Vector create(float x, float y, float z) {
        return VoidCR.getMagic().createVector(x, y, z);
    }

    static Vector fromBytes(byte[] input) {
        return VoidCR.getMagic().deserialize(Vector.class, input);
    }
}
