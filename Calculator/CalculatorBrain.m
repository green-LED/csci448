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
    } else {
        result = (1/[self popOperand]) * [self popOperand];
    }
    
    [self pushOperand:result];
    
    return result;
}

@end
