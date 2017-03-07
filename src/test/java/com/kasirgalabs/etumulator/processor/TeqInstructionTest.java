/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.processor;

import static org.junit.Assert.assertEquals;

import com.kasirgalabs.etumulator.InstructionTester;
import org.junit.Test;

public class TeqInstructionTest extends InstructionTester {
    /**
     * Test of exitTeq method, of class Processor.
     */
    @Test
    public void testExitTeq() {
        char[] code = ("mov r1, #0\n"
                + "mov r2, #1\n"
                + "teq r1, r2\n").toCharArray();
        runTestCode(code);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());

        code = ("ldr r1, =0xffffffff\n"
                + "teq r1, r1\n").toCharArray();
        runTestCode(code);
        assertEquals("Negative flag is wrong.", false, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", true, cpsr.isZero());

        code = ("ldr r1, =0xffffffff\n"
                + "teq r1, 0\n").toCharArray();
        runTestCode(code);
        assertEquals("Negative flag is wrong.", true, cpsr.isNegative());
        assertEquals("Zero flag is wrong.", false, cpsr.isZero());
    }
}
