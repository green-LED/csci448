//
//  ViewController.m
//  Calculator
//
//  Created by Matthew Wichmann on 1/18/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import "ViewController.h"
#import "CalculatorBrain.h"

@interface ViewController ()
@property (nonatomic) BOOL userIsEntering;
@property (nonatomic, strong) CalculatorBrain *brain;
@end

@implementation ViewController

@synthesize display;
@synthesize history;
@synthesize userIsEntering;
@synthesize brain = _brain;

- (CalculatorBrain *)brain {
    if (!_brain) _brain = [[CalculatorBrain alloc] init];
    return _brain;
}

// For every number entered and operation hit, append it to the history
// The label automatically cuts the text off with "..." if it gets too long
- (IBAction)addHistory:(NSString *)entry {
    self.history.text = [self.history.text stringByAppendingString:entry];
    self.history.text = [self.history.text stringByAppendingString:@" "];
    //if (self.history.text.length > 40) {
        //self.history.text = [self.history.text substringToIndex:40];
    //}
}

- (IBAction)digitPressed:(UIButton *)sender {
    NSString *digit = [sender currentTitle];
    if (self.userIsEntering) {
        self.display.text = [self.display.text stringByAppendingString:digit];
    } else {
        self.display.text = digit;
        self.userIsEntering = YES;
    }
}

- (IBAction)dotpressed {
    if (self.userIsEntering) {
        if (![self.display.text containsString:@"."]) {
            self.display.text = [self.display.text stringByAppendingString:@"."];
        }
    } else {
        self.display.text = @"0.";
        self.userIsEntering = YES;
    }
}

- (IBAction)enterPressed {
    [self.brain pushOperand:[self.display.text doubleValue]];
    [self addHistory:self.display.text];
    self.userIsEntering = NO;
}

- (IBAction)operationPressed:(id)sender {
    if (userIsEntering) {
        [self enterPressed];
    }
    NSString *operation = [sender currentTitle];
    double result = [self.brain performOperation:operation];
    // If result is inf (either nan or inf from performOpreation), display "Error"
    if (result == INFINITY) {
        self.display.text = @"Error";
    } else {
        self.display.text = [NSString stringWithFormat:@"%g", result];
    }
    [self addHistory:operation];
}

- (IBAction)clearAll {
    [self.brain clearOperands];
    self.userIsEntering = NO;
    self.display.text = @"0";
    self.history.text = @"";
}

// undo last digit (or dot) pressed.
// If last digit is erased, stop entering
- (IBAction)backspace {
    if (self.userIsEntering) {
        self.display.text = [self.display.text substringToIndex:self.display.text.length - 1];
        if (self.display.text.length == 0) {
            self.userIsEntering = NO;
            self.display.text = @"0";
        }
    }
}


@end
