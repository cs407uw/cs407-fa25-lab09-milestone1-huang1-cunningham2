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

        // X-axis
        // Update position
        val distanceX = velocityX * dT + (1f / 6f) * dT * dT * (3f * accX + xAcc)
        this.posX += distanceX
        checkBoundaries()
        
        // Update velocity
        this.velocityX += 0.5*(xAcc + this.accX)*(dT)

        // Update acceleration
        this.accX = xAcc
        
        // Y-axis
        // Update position
        val distanceY = velocityY * dT + (1f / 6f) * dT * dT * (3f * accY + yAcc)
        this.posY += distanceY

        // Update velocity
        this.velocityY += 0.5*(yAcc + this.accY)*(dT)

        // Update acceleration
        this.accY = yAcc  

        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // Check all 4 walls
        if (this.posX - ballSize / 2f <= 0) { // Left wall
            this.velocityX = 0f
            this.accX = 0f
        } else if (this.posX + ballSize / 2f >= backgroundWidth) { // Right wall
            this.velocityX = 0f
            this.accX = 0f
        } else if (this.posY + ballSize / 2f >= backgroundHeight) { // Top wall
            this.velocityY = 0f
            this.accY = 0f
        } else if (this.posY - ballSize / 2f <= 0) { // Bottom wall
            this.velocityY = 0f
            this.accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {        
        this.posX = backgroundWidth / 2f
        this.posY = backgroundHeight / 2f
        this.velocityX = 0f
        this.velocityY = 0f
        this.accX = 0f
        this.accY = 0f
        isFirstUpdate = true
    }
}
