		Stack<Integer> stack = new Stack<>();
		boolean answer = true;
		boolean first = false;
		if (se[0] == -1) {
			if (se.length == 1) {
				answer = true;
			} else {
				answer = false;
			}
			return;
		}
		stack.add(-1);
		for (int i = 0; i < se.length; ++i) {
			if (stack.isEmpty()) {
				answer = false;
				break;
			}
			if (se[i] != -1) {
				stack.pop();
				stack.add(se[i]);
				stack.add(se[i]);
				continue;
			} else {
				if (stack.isEmpty()) {
					answer = false;
					break;
				} 
				else {
					stack.pop();
				}
			}
		}
		if(!stack.isEmpty()) {
			answer = false;
		}