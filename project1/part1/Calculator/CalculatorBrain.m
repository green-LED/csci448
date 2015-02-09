//
//  CalculatorBrain.m
//  Calculator
//
//  Created by Matthew Wichmann on 1/20/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import "CalculatorBrain.h"

@interface CalculatorBrain()
@property (nonatomic, strong) NSMutableArray *operandStack;
@end

@implementation CalculatorBrain

@synthesize operandStack = _operandStack;

- (NSMutableArray*)operandStack {
    if (!_operandStack) {
        _operandStack = [[NSMutableArray alloc] init];
    }
    return _operandStack;
}

- (void)pushOperand:(double)operand {
    NSNumber *operandObject = [NSNumber numberWithDouble:operand];
    [self.operandStack addObject:operandObject];
}

- (double)popOperand {
    NSNumber *lastOperand = [self.operandStack lastObject];
    if (lastOperand) {
        [self.operandStack removeLastObject];
    }
    return [lastOperand doubleValue];
}

- (double)performOperation:(NSString*)operation {
    double result = 0;
    
    if ([operation isEqualToString:@"+"]) {
        result = [self popOperand] + [self popOperand];
    } else if ([operation isEqualToString:@"*"]) {
        result = [self popOperand] * [self popOperand];
    } else if ([operation isEqualToString:@"-"]) {
        result = (-1)*[self popOperand] + [self popOperand];
    } else if ([operation isEqualToString:@"/"]) {
        double divisor = [self popOperand];
        // If input is 0, return it to stack and return inf
        if (divisor == 0) {
            [self pushOperand:divisor];
            return 1.0/0;
        }
        result = [self popOperand]/divisor;
    } else if ([operation isEqualToString:@"sin"]) {
        result = sin([self popOperand]);
    } else if ([operation isEqualToString:@"cos"]) {
        result = cos([self popOperand]);
    } else if ([operation isEqualToString:@"sqrt"]) {
        // If input is negative, return it to stack and return inf
        double operand = [self popOperand];
        if (operand < 0) {
            [self pushOperand:operand];
            return 1.0/0;
        }
        result = sqrt(operand);
    } else if ([operation isEqualToString:@"pi"]) {
        result = M_PI;
    } else {
        result = [self popOperand];
    }
    
    [self pushOperand:result];
    
    return result;
}

- (void)clearOperands {
    [self.operandStack removeAllObjects];
}

@end
