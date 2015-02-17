//
//  CalculatorBrain.h
//  Calculator
//
//  Created by CS193p Instructor.
//  Copyright (c) 2011 Stanford University.
//  All rights reserved.
//

#import "CalculatorBrain.h"

@interface CalculatorBrain()

@property (nonatomic, strong) NSMutableArray *programStack;


@end

@implementation CalculatorBrain

@synthesize programStack = _programStack;

- (NSMutableArray *)programStack {
	if (_programStack == nil) _programStack = [[NSMutableArray alloc] init];
	return _programStack;
}

- (id)program {
    return [self.programStack copy];
}

+ (NSString *)descriptionOfProgram:(id)program {
    
    NSString *desc = @"";
    
    for (int i=0; i < [program count]; i++) {
        id obj = [program objectAtIndex:i];
        NSString *add;
        if ([obj isKindOfClass:[NSString class]]) {
            add = obj;
        } else if ([obj isKindOfClass:[NSNumber class]]){
            NSLog(@"%@, a %@", obj, [obj class]);
            add = [(NSNumber *) obj stringValue];
        }
        desc = [desc stringByAppendingString:add];
        desc = [desc stringByAppendingString:@" "];
    }
    
    return desc;
}

+ (id)popOperandOffStack:(NSMutableArray *) stack {
    
    double result = 0;
    
    id topOfStack = [stack lastObject];
    NSLog(@"%@", topOfStack);
    if (topOfStack) [stack removeLastObject]; else return @"0";
    
    
    if ([topOfStack isKindOfClass:[NSNumber class]]) return topOfStack;
    
    NSString *operation = topOfStack;
    
    if ([operation isEqualToString:@"pi"]) {
        result = M_PI;
    } else {
        
        double op1;
        topOfStack = [self popOperandOffStack:stack];
        if ([topOfStack isKindOfClass:[NSNumber class]]) {
            op1 = [(NSNumber *) topOfStack doubleValue];
        } else {
            op1 = 0;
        }
        
        if ([operation isEqualToString:@"sin"]) {
            result = sin(op1);
        } else if ([operation isEqualToString:@"cos"]) {
            result = cos(op1);
        } else if ([operation isEqualToString:@"sqrt"]) {
            /*if (operand < 0) {
              
            }*/
            result = sqrt(op1);
        } else {
            
            double op2;
            topOfStack = [self popOperandOffStack:stack];
            if (![topOfStack isKindOfClass:[NSNumber class]]) {
                op2 = [(NSNumber *) topOfStack doubleValue];
            } else {
                op2 = 0;
            }
            if ([operation isEqualToString:@"+"]) {
                result = op1 + op2;
            } else if ([operation isEqualToString:@"*"]) {
                result = op1 * op2;
            } else if ([operation isEqualToString:@"-"]) {
                result = op2 - op1;
            } else if ([operation isEqualToString:@"/"]) {
                /*if (divisor == 0) {
                 
                }*/
                result = op2 / op1;
            }
            
        }
        
    }
    return [NSNumber numberWithDouble:result];
}

- (void)pushOperand:(double)operand {
    NSNumber *operandObject = [NSNumber numberWithDouble:operand];
    [self.programStack addObject:operandObject];
}

- (void)pushVariable:(NSString *) variable {
    [self.programStack addObject:variable];
}
- (void)pushOperation:(NSString *) operation {
    [self.programStack addObject:operation];
}

+ (id)runProgram:(id)program {
	// Call the new runProgram method with a nil dictionary
	return [self runProgram:program usingVariableValues:nil];
}

+ (id)runProgram:(id)program 
usingVariableValues:(NSDictionary *)variableValues {
	
	
	NSMutableArray *stack= [program mutableCopy];
	
	// For each item in the program
	for (int i=0; i < [stack count]; i++) {
		id obj = [stack objectAtIndex:i]; 
		
		// See whether we think the item is a variable
		if ([obj isKindOfClass:[NSString class]] && [obj isEqualToString:@"x"]) {
			id value = [variableValues objectForKey:obj];			
			// If value is not an instance of NSNumber, set it to zero
			if (![value isKindOfClass:[NSNumber class]]) {
				value = [NSNumber numberWithInt:0];
			}			
			// Replace program variable with value.
			[stack replaceObjectAtIndex:i withObject:value];
		}		
	}	
	// Starting popping off the stack
	return [self popOperandOffStack:stack];
}

- (void)clearPrograms {
    [self.programStack removeAllObjects];
}

@end
