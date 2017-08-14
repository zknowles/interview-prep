def count_unival(node):
	count = 0

	def is_unival(node):
		if not node:
			return True

		left = is_unival(node.left)
		right = is_unival(node.right)
		
		if left and right and (not left or node.left.value == node.value) \
			and (not right or node.right.value == node.value):
			count += 1
			return True

		return False

	is_unival(tree)
	return count