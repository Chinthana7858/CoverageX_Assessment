// src/components/TaskCard.test.tsx
import { describe, it, expect, vi } from 'vitest';
import { render, screen, fireEvent } from '@testing-library/react';
import TaskCard from '../components/TaskCard';
import '@testing-library/jest-dom';

describe('TaskCard', () => {
  const mockTask = {
    id: 1,
    title: 'Test Task',
    description: 'Test description',
    isDone: false,
    createdAt: '',
  };

  const onDone = vi.fn();

  it('renders task details', () => {
    render(<TaskCard {...mockTask} onDone={onDone} />);
    expect(screen.getByText('Test Task')).toBeInTheDocument();
    expect(screen.getByText('Test description')).toBeInTheDocument();
  });

  it('calls onDone when Done button is clicked', () => {
    render(<TaskCard {...mockTask} onDone={onDone} />);
    fireEvent.click(screen.getByText(/Done/i));
    expect(onDone).toHaveBeenCalledWith(1);
  });
});