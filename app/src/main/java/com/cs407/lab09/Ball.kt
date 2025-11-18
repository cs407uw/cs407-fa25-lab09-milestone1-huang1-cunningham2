package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        if (xAcc !== 0.0f) { // X-axis acceleration
            // Update position
            val distanceX = velocityX * dT + (1f / 6f) * dT * dT * (3f * accX + xAcc)
            this.posX = (this.velocityX < 0) ? this.posX - distanceX : this.posX + distanceX
            
            // Update velocity
            this.velocityX += 0.5*(xAcc + this.accX)*(dT)
        } else { // Y-axis acceleration
            // Update position
            val distanceY = velocityY * dT + (1f / 6f) * dT * dT * (3f * accY + yAcc)
            this.posY = (this.velocityY < 0) ? this.posY - distanceY : this.posY + distanceY

            // Update velocity
            this.velocityY += 0.5*(yAcc + this.accY)*(dT)
        }   
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
    }
}
