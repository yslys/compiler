	.data
	.align2
.numValidInputs:	.space4
	.text
_multiplyTwoNumbers:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	mult  $t0, $t1
	mflo  $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	lw    $t0, 4($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	mult  $t0, $t1
	mflo  $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	beq   $t0, $t1, .L2
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sle   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	j     .L1
.L2:
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
.L1:
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	beq   $t0, $t1, .L4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sge   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	j     .L3
.L4:
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
.L3:
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L6
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	beq   $t0, $t1, .L8
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sle   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	j     .L7
.L8:
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
.L7:
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	beq   $t0, $t1, .L10
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sge   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	j     .L9
.L10:
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
.L9:
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L12
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	beq   $t0, $t1, .L14
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	slt   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	j     .L13
.L14:
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
.L13:
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L16
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L0
	j     .L15
.L16:
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L0
	nop
.L15:
	j     .L11
.L12:
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L0
	nop
.L11:
	j     .L5
.L6:
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L0
	nop
.L5:
.L0:
			#FUNCTION EXIT
_multiplyTwoNumbers_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	jr    $ra
multiplyTwoNumbers:
	.text
_doFactorialRecur:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sle   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L19
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L17
	j     .L18
.L19:
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sub   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   doFactorialRecur
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   multiplyTwoNumbers
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L17
	nop
.L18:
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L17
.L17:
			#FUNCTION EXIT
_doFactorialRecur_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	jr    $ra
doFactorialRecur:
	.text
_doFactorialItr:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
.L21:
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	slt   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L22
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	add   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   multiplyTwoNumbers
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	j     .L21
.L22:
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L20
.L20:
			#FUNCTION EXIT
_doFactorialItr_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	jr    $ra
doFactorialItr:
	.text
_isDone:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	li    $t0, 999
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $v0, 4($sp)	#POP
	addu  $sp, $sp, 4
	j     .L23
.L23:
			#FUNCTION EXIT
_isDone_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	jr    $ra
isDone:
	.text
_loopTilDone:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	.data
.L25:	.asciiz "("
	.text
	la    $t0, .L25
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, _numValidInputs
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L26:	.asciiz ") "
	.text
	la    $t0, .L26
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	.data
.L27:	.asciiz "Enter a number to take the factorial of, -1 to exit: "
	.text
	la    $t0, .L27
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	li    $v0, 5
	syscall
	sw    $v0, 0($fp)
.L28:
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   isDone
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	xor   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L29
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 0
	sub   $t0, $t1, $t0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	slt   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L30
	.data
.L31:	.asciiz "You entered a number less than -1, please try a different number.\n"
	.text
	la    $t0, .L31
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	jal   loopTilDone
	addu  $sp, $sp, 0
	sw    $v0, $fp
	j     .L24
	nop
.L30:
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   doFactorialRecur
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	jal   doFactorialItr
	la    $t0, $fp, 0
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	.data
.L32:	.asciiz "The recursive answer is "
	.text
	la    $t0, .L32
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L33:	.asciiz ".\n"
	.text
	la    $t0, .L33
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	.data
.L34:	.asciiz "The iterative answer is "
	.text
	la    $t0, .L34
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, 0($fp)
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L35:	.asciiz ".\n"
	.text
	la    $t0, .L35
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $t1, 1
	bne   $t0, $t1, .L37
	.data
.L38:	.asciiz "These answers match.\n"
	.text
	la    $t0, .L38
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	j     .L36
.L37:
	.data
.L39:	.asciiz "These answers do NOT match.\n"
	.text
	la    $t0, .L39
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	nop
.L36:
	lw    $t0, _numValidInputs
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	add   $t0, $t0, $t1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, _numValidInputs
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	.data
.L40:	.asciiz "("
	.text
	la    $t0, .L40
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	lw    $t0, _numValidInputs
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
	.data
.L41:	.asciiz ") "
	.text
	la    $t0, .L41
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	.data
.L42:	.asciiz "Enter a number to take the factorial of, -1 to exit: "
	.text
	la    $t0, .L42
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	li    $v0, 5
	syscall
	sw    $v0, 0($fp)
	j     .L28
.L29:
			#FUNCTION EXIT
_loopTilDone_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	jr    $ra
loopTilDone:
	.text
	.globl main
main:		# METHOD ENTRY
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0

	li    $t0, 1
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	la    $t0, _numValidInputs
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	#POP
	addu  $sp, $sp, 4
	lw    $t0, 4($sp)	#POP
	addu  $sp, $sp, 4
	sw    $t0, 0($t1)
	.data
.L44:	.asciiz "Program is starting...\n"
	.text
	la    $t0, .L44
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	jal   loopTilDone
	addu  $sp, $sp, 0
	sw    $v0, $fp
	.data
.L45:	.asciiz "Program is terminating...\n"
	.text
	la    $t0, .L45
	sw    $t0, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	lw    $a0, 4($sp)	#POP
	addu  $sp, $sp, 4
	li    $v0, 4
	syscall
	j     .L43
			#FUNCTION EXIT
_main_Exit:
	lw    $ra, 0($fp)	#load return address
	move  $t0, $fp, save control link
	lw    $fp, -4($fp)	#restore FP
	move  $sp, $t0		#restore SP
	li    $v0, 10		#load exit code for syscall
	syscall		#only do this for main
main:
